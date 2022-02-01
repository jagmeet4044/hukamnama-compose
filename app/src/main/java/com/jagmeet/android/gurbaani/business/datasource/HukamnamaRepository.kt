package com.jagmeet.android.gurbaani.business.datasource

import com.jagmeet.android.gurbaani.business.datasource.remote.HukamnamaNetworkSource
import com.jagmeet.android.gurbaani.business.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.business.model.Result
import com.jagmeet.android.gurbaani.business.util.ApiResult
import com.jagmeet.android.gurbaani.business.util.toHukamnama
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HukamnamaRepository  constructor(
) {
    private var hukamnamaNetworkSource: HukamnamaNetworkSource = HukamnamaNetworkSource()

    fun getHukamnama(): Flow<Result<HukamnamaDetail?>> = flow {
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