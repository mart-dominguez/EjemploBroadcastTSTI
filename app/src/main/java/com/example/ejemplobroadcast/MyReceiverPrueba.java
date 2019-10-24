package com.example.ejemplobroadcast;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Map;
import java.util.Set;

public class MyReceiverPrueba extends BroadcastReceiver {
    public static final String TAG = "APP_BR";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG," ACCION: "+intent.getAction());
        if(intent.getExtras()!=null){
            Set<String> claves = intent.getExtras().keySet();
            for(String unaClave : claves){
                Log.d(TAG," Extras valor "+ unaClave + ": "+intent.getExtras().get(unaClave));
            }
        }
        Intent destino = new Intent(context, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, destino, 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, "999")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(android.R.drawable.ic_dialog_map)
                        .setContentTitle(intent.getExtras().getString("data1"))
                        .setContentText(intent.getExtras().getString("data2"))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification n = mBuilder.build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(99, n);
    }
}
