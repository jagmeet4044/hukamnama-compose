package com.jagmeet.android.gurbaani.business.datasource.remote

import com.jagmeet.android.gurbaani.business.datasource.remote.api.HukamnamaEndPoint
import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama

class HukamnamaNetworkSource(private val apiEndPoint: HukamnamaEndPoint) {
    suspend fun getHukamnama(): TodayHukamnama {
        return apiEndPoint.getHukamnama()
    }
}