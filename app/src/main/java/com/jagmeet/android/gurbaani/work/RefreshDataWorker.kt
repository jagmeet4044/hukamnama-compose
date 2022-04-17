package com.jagmeet.android.gurbaani.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jagmeet.android.gurbaani.Result
import com.jagmeet.android.gurbaani.datasource.repository.hukamnama.HukamnamaRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted params: WorkerParameters,
    private val hukamnamaRepository: HukamnamaRepository
) : CoroutineWorker(
    appContext,
    params
) {


    companion object {
        const val WORK_NAME = "com.jagmeet.android.gurbaani.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        Timber.d("RefreshDataWorker-doWork start")
        hukamnamaRepository.getHukamnama(true).collect {
            when (it.status) {
                com.jagmeet.android.gurbaani.Result.Status.SUCCESS -> {
                 //   WorkerUtil.makeStatusNotification(appContext)
                }
                com.jagmeet.android.gurbaani.Result.Status.ERROR -> {
                    Result.retry()
                }
            }
        }
        return Result.success()
    }

}