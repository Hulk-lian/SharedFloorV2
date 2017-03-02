package com.jtcode.sharedfloor;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Hulk-lián
 */

public class MakeNotification {

    public static void createNotification(Context context,int smallIcon,String title,String content){

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context).setSmallIcon(smallIcon).setAutoCancel(true).setContentTitle(title)
                .setContentText(content);
        Intent notIntentIntent=new Intent(context,Activity_Selection.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,notIntentIntent,0);
     builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)SharedFloorApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}
