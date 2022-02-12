package com.jagmeet.android.gurbaani.business.datasource


import com.jagmeet.android.gurbaani.util.ApiResult
import com.jagmeet.android.gurbaani.util.Constants.Companion.NETWORK_TIMEOUT
import com.jagmeet.android.gurbaani.util.ErrorHandling.Companion.NETWORK_ERROR_TIMEOUT
import com.jagmeet.android.gurbaani.util.ErrorHandling.Companion.UNKNOWN_ERROR
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

private val TAG: String = "AppDebug"

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(NETWORK_TIMEOUT) {
                ApiResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    ApiResult.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ApiResult.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    ApiResult.GenericError(
                        null,
                        UNKNOWN_ERROR
                    )
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.toString()
    } catch (exception: Exception) {
        UNKNOWN_ERROR
    }
}
