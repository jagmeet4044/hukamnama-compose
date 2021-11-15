package com.jagmeet.android.gurbaani.business.datasource.remote.api

import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import retrofit2.http.GET

interface HukamnamaEndPoint {
    @GET("hukamnama")
    suspend fun getHukamnama(): TodayHukamnama
}