package com.jagmeet.android.gurbaani.business.datasource.remote

import com.jagmeet.android.gurbaani.business.datasource.remote.api.HukamnamaEndPoint
import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.util.ApiResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HukamnamaNetworkSource{

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

    public suspend fun getHukamnama(): ApiResult<TodayHukamnama> {
        var todayHukamnama = getRetrofit()?.create(HukamnamaEndPoint::class.java)?.getHukamnama()
        if (todayHukamnama != null)
            return ApiResult.Success<TodayHukamnama>(todayHukamnama)
        else
            return ApiResult.NetworkError
    }
}