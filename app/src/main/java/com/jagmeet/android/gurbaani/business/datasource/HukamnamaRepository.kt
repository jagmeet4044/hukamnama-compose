package com.jagmeet.android.gurbaani.business.datasource

import com.jagmeet.android.gurbaani.business.datasource.remote.HukamnamaNetworkSource
import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HukamnamaRepository @Inject constructor(
    var hukamnamaNetworkSource: HukamnamaNetworkSource
) {
    fun getHukamnama(): Flow<Result<TodayHukamnama?>> = flow {
        val apiResult = safeApiCall(Dispatchers.IO) {
            hukamnamaNetworkSource.getHukamnama()
        }

        when (apiResult) {
            is ApiResult.Success -> {
                emit(Result.success(apiResult.value))
            }
        }

    }
}