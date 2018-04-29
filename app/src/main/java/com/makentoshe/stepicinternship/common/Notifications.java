package com.makentoshe.stepicinternship.common;

import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;

import com.makentoshe.stepicinternship.R;

/**
 * Created by Makentoshe on 29.04.2018.
 */

public class Notifications {

    public static boolean downloadingProgress
            (int id, Context context, int max, int progress,
             boolean indeterminate, String courseName, String lesson) {

        android.app.Notification.Builder builder = new android.app.Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(courseName)
                .setContentText(lesson)
                .setProgress(max, progress, indeterminate);
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(id, builder.getNotification());
            return true;
        } else {
            return false;
        }
    }

    public static boolean downloadingNotification
            (int id, Context context, String courseName, String lesson) {

        android.app.Notification.Builder builder = new android.app.Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(courseName)
                .setContentText(lesson);
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(id, builder.getNotification());
            return true;
        } else {
            return false;
        }
    }
}
