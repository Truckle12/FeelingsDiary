package com.example.ssteffanus.feelingsdiary;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Notification;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by harrisonpierce on 12/3/15.
 */
public class NotificationService extends BroadcastReceiver{
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("RECEIVER", "on receive");
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.default_image)
                        .setContentTitle("FeelingsDiary")
                        .setContentText("How Are You Feeling?");

        Intent openMain = new Intent(context, FeelingEntryActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openMain, 0);
        mBuilder.setContentIntent(pendingIntent);
        notificationManager.notify(0, mBuilder.build());
    }
}
