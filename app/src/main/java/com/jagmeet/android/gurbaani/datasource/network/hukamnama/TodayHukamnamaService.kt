package com.jagmeet.android.gurbaani.datasource.network.hukamnama

import com.jagmeet.android.gurbaani.datasource.network.hukamnama.hukamnama_data.TodayHukamnama
import retrofit2.http.GET

interface TodayHukamnamaService {
    @GET("hukamnama")
    suspend fun getHukamnama(): TodayHukamnama
}