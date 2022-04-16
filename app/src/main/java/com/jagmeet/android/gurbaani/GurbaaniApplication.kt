package com.jagmeet.android.gurbaani

import android.app.Application
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.jagmeet.android.gurbaani.work.RefreshDataWorker
import com.jagmeet.android.gurbaani.work.WorkerUtil
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltAndroidApp
class GurbaaniApplication : Application(), Configuration.Provider {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()


    override fun onCreate() {
        super.onCreate()
        delayedInit()
        setupLogger()
    }

    private fun setupLogger() {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(
                priority: Int, tag: String?, message: String, t: Throwable?
            ) {
                super.log(priority, "Hukamnama_today_$tag", message, t)
            }
        })
    }

    private fun delayedInit() {
        applicationScope.launch {
            //  Timber.plant(Timber.DebugTree())
            setupRecurringWork()
        }
    }

    /**
     * Setup WorkManager background job to 'fetch' new network data daily.
     */
    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            //  .setRequiredNetworkType(NetworkType.UNMETERED)
            // .setRequiresCharging(true)
            .setRequiresBatteryNotLow(false)
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(false)
                }
            }
            .build()

        val repeatingRequest =
            PeriodicWorkRequestBuilder<RefreshDataWorker>(WorkerUtil.getDelayInMinutes(), TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MAX_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}