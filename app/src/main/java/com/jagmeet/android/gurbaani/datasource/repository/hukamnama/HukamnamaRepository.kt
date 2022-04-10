package com.jagmeet.android.gurbaani.datasource.repository.hukamnama

import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import kotlinx.coroutines.flow.Flow

interface HukamnamaRepository {
    suspend fun getHukamnama(): Flow<Result<HukamnamaDetail?>>
}