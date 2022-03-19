package com.jagmeet.android.gurbaani.datasource.repository.hukamnama

import android.util.Log
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.datasource.database.HukamnamaDb
import com.jagmeet.android.gurbaani.datasource.database.asDomainModel
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.TodayHukamnamaService
import com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data.TodayHukamnama
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.util.asDbInfo
import com.jagmeet.android.gurbaani.util.toHukamnama
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HukamnamaRepository constructor(val database: HukamnamaDb) {
    private var mRetrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            val builder = OkHttpClient.Builder()
            val okHttpClient = builder.build()
            mRetrofit = Retrofit.Builder().baseUrl("https://api.gurbaninow.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return mRetrofit
    }

    suspend fun getHukamnama(): Flow<Result<HukamnamaDetail?>> = flow {
        var hukamnama = database.hukamnamaDao.getHukamNama()
        if (hukamnama != null) {
            Log.d("jagmeet_nir", "got from cache")
            emit(Result.success(hukamnama.asDomainModel()))
        } else {
            val result = refreshHukamnama()
            when (result.status) {
                Result.Status.SUCCESS -> {
                    val hukam = result.data as TodayHukamnama
                    val dbObject = hukam.asDbInfo();
                    database.hukamnamaDao.insert(dbObject)
                    var hukamnama = database.hukamnamaDao.getHukamNama()
                    Log.d("jagmeet_nir", "emit success")
                    emit(Result.success(hukam.toHukamnama()))
                }
                Result.Status.ERROR -> {
                    emit(Result.error("failed to get latest Hukamnama", null))
                }
            }

        }
    }.catch { e -> handleUseCaseException(e) }

    suspend fun refreshHukamnama(): Result<TodayHukamnama> {
        Log.d("jagmeet_nir", "refreshHukamnama")
        var todayHukamnamaService = getRetrofit()?.create(TodayHukamnamaService::class.java)
        var todayHukamnama = todayHukamnamaService?.getHukamnama()
        if (todayHukamnama != null) {
            return Result.success(todayHukamnama)
        } else
            return Result.error("network error", null)
    }

    private fun handleUseCaseException(e: Throwable): Result<HukamnamaDetail?> {
        e.printStackTrace()
        Log.d("jagmeet_nir", "handleUseCaseException ${e.message}")
        when (e) {
            is HttpException -> {
                return Result.error("network error", null)
            }
            else -> {
                return Result.error("something went wrong", null);
            }
        }
    }
}