package com.example.myworkmnotification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerUpdateNotify(context: Context, workerParams : WorkerParameters) : Worker(
    context,
    workerParams
) {
        override fun doWork(): Result {
            val intent = Intent(applicationContext, DetailActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
/*                .putExtra(NOTIFICATION_ID, 2)*/
            val goToDetail = PendingIntent.getActivity(
                applicationContext,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder = NotificationCompat.Builder(applicationContext, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Apte4ka")
                .setContentText("У препарата заканчивается срок годности!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(goToDetail)
                .setAutoCancel(true)
                .build()
            NotificationManagerCompat.from(applicationContext).notify(MainActivity.NOTIFICATION_ID++, builder)
            return Result.success()
        }
}