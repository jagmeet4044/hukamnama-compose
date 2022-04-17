package com.jagmeet.android.gurbaani.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jagmeet.android.gurbaani.Constants.REFRESH_AT_HOUR
import com.jagmeet.android.gurbaani.R
import org.joda.time.DateTime
import org.joda.time.Duration
import timber.log.Timber


class WorkerUtil {
    companion object {
        private const val CHANNEL_ID: String = "Today's Hukamnama"

        fun getDelayInMinutes(): Long {
            var delay = 0L
            delay = if (DateTime.now().hourOfDay < REFRESH_AT_HOUR) {
                Duration(
                    DateTime.now(),
                    DateTime.now().withTimeAtStartOfDay().plusHours(REFRESH_AT_HOUR)
                ).standardMinutes
            } else {
                Duration(
                    DateTime.now(),
                    DateTime.now().withTimeAtStartOfDay().plusDays(1).plusHours(REFRESH_AT_HOUR)
                ).standardMinutes
            }
            Timber.d("delay of $delay")
            return delay
        }

        fun makeStatusNotification(context: Context) {
            // Make a channel if necessary
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                val name = "Hukamnama"
                val description = "Daily hukamnama"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNEL_ID, name, importance)
                channel.description = description

                // Add the channel
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

                notificationManager?.createNotificationChannel(channel)
            }

            // Create the notification
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Today's Hukamnama")
                .setContentText("Click to read")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(LongArray(0))

            // Show the notification
            NotificationManagerCompat.from(context).notify(0, builder.build())


        }
    }

}