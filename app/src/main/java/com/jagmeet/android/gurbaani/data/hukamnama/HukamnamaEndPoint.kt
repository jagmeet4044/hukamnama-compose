package com.jagmeet.android.gurbaani.data.hukamnama

import com.jagmeet.android.gurbaani.data.hukamnama.hukamnamaData.TodayHukamnama
import retrofit2.http.GET

interface HukamnamaEndPoint {
    @GET("hukamnama")
    suspend fun getHukamnama(): TodayHukamnama
}