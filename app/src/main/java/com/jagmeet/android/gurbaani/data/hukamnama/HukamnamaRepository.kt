package com.jagmeet.android.gurbaani.data.hukamnama

import com.jagmeet.android.gurbaani.data.hukamnama.HukamnamaNetworkSource
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.util.ApiResult
import com.jagmeet.android.gurbaani.util.toHukamnama
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HukamnamaRepository {
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