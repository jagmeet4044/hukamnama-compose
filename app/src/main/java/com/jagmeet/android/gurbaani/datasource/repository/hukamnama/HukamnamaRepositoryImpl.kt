package com.jagmeet.android.gurbaani.datasource.repository.hukamnama

import android.util.Log
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.datasource.database.HukamnamaDao
import com.jagmeet.android.gurbaani.datasource.database.asDomainModel
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.TodayHukamnamaService
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data.TodayHukamnama
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.util.asDbInfo
import com.jagmeet.android.gurbaani.util.toHukamnama
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


class HukamnamaRepositoryImpl @Inject constructor(
    private val hukamnamaDao: HukamnamaDao,
    private val todayHukamnamaService: TodayHukamnamaService
) : HukamnamaRepository {

    override suspend fun getHukamnama(): Flow<Result<HukamnamaDetail?>> = flow {
        var hukamnama = hukamnamaDao.getHukamNama()
        if (hukamnama != null) {
            Log.d("jagmeet_nir", "got from cache")
            emit(Result.success(hukamnama.asDomainModel()))
        } else {
            val result = refreshHukamnama()
            when (result.status) {
                Result.Status.SUCCESS -> {
                    val hukam = result.data as TodayHukamnama
                    val dbObject = hukam.asDbInfo();
                    hukamnamaDao.insert(dbObject)
                    var hukamnama = hukamnamaDao.getHukamNama()
                    Log.d("jagmeet_nir", "emit success")
                    emit(Result.success(hukam.toHukamnama()))
                }
                Result.Status.ERROR -> {
                    emit(Result.error("failed to get latest Hukamnama", null))
                }
            }

        }
    }.catch { e -> handleUseCaseException(e) }

    private suspend fun refreshHukamnama(): Result<TodayHukamnama> {
        Log.d("jagmeet_nir", "refreshHukamnama")
        var todayHukamnama = todayHukamnamaService?.getHukamnama()
        return if (todayHukamnama != null) {
            Result.success(todayHukamnama)
        } else
            Result.error("network error", null)
    }

    private fun handleUseCaseException(e: Throwable): Result<HukamnamaDetail?> {
        e.printStackTrace()
        Log.d("jagmeet_nir", "handleUseCaseException ${e.message}")
        return when (e) {
            is HttpException -> {
                Result.error("network error", null)
            }
            else -> {
                Result.error("something went wrong", null);
            }
        }
    }
}