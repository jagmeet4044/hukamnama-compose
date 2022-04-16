package com.jagmeet.android.gurbaani.datasource.repository.hukamnama

import android.util.Log
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.datasource.database.HukamnamaDao
import com.jagmeet.android.gurbaani.datasource.database.asDomainModel
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.TodayHukamnamaService
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data.TodayHukamnama
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.util.asDbInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


class HukamnamaRepositoryImpl @Inject constructor(
    private val hukamnamaDao: HukamnamaDao,
    private val todayHukamnamaService: TodayHukamnamaService
) : HukamnamaRepository {

    override suspend fun getHukamnama(forceFetch: Boolean): Flow<Result<HukamnamaDetail?>> = flow {
        if (forceFetch) {
            fetch(this)
        } else {
            var hukamnama = hukamnamaDao.getHukamNama()
            if (hukamnama != null) {
                Log.d("jagmeet_work", "got from cache")
                emit(Result.success(hukamnama.asDomainModel()))
            } else {
                fetch(this)
            }

        }
    }.catch { e -> handleUseCaseException(e) }

    private suspend fun fetch(
        flowCollector: FlowCollector<Result<HukamnamaDetail>>
    ) {
        val result = fetchHukamnama()
        delay(1000)
        when (result.status) {
            Result.Status.SUCCESS -> {
                val hukam = result.data as TodayHukamnama
                val dbObject = hukam.asDbInfo();
                hukamnamaDao.insert(dbObject)
                var hukamnama = hukamnamaDao.getHukamNama()
                Log.d("jagmeet_work", "emit success")
                flowCollector.emit(Result.success(hukamnama.asDomainModel()))
            }
            Result.Status.ERROR -> {
                flowCollector.emit(Result.error("failed to get latest Hukamnama", null))
            }
        }
    }


    private suspend fun fetchHukamnama(): Result<TodayHukamnama> {
        Log.d("jagmeet_work", "refreshHukamnama")
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