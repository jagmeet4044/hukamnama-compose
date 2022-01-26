package com.jagmeet.android.gurbaani.business.datasource

import com.jagmeet.android.gurbaani.business.datasource.remote.HukamnamaNetworkSource
import com.jagmeet.android.gurbaani.business.datasource.remote.api.hukamnamaData.TodayHukamnama
import com.jagmeet.android.gurbaani.business.model.Hukamnama
import com.jagmeet.android.gurbaani.business.model.Result
import com.jagmeet.android.gurbaani.business.util.ApiResult
import com.jagmeet.android.gurbaani.business.util.toHukamnama
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class HukamnamaRepository @Inject constructor(
) {
    private var hukamnamaNetworkSource: HukamnamaNetworkSource = HukamnamaNetworkSource()

    fun getHukamnama(): Flow<Result<Hukamnama?>> = flow {
        val apiResult =
            hukamnamaNetworkSource.getHukamnama()

        when (apiResult) {
            is ApiResult.Success -> {
                emit(Result.success(apiResult.value.toHukamnama()))
            }
            is ApiResult.NetworkError -> {
                emit(Result.error("network error", null))
            }
        }

    }
}