package com.example.ejemplobroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver br;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.createNotificationChannel();
        br = new MyReceiverPrueba();
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter filtro2 = new IntentFilter();
        filtro2 .addAction(MyIntentService.ENVIAR_NOTIF1);
        filtro2 .addAction(MyIntentService.ENVIAR_NOTIF2);
//        this.registerReceiver(br, filter);
        this.registerReceiver(br, filtro2);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(MyReceiverPrueba.TAG," CLICK EN BOTON");
                //Intent intent = new Intent(MainActivity.this, MyReceiverPrueba.class);
                //intent.setAction(MyIntentService.ENVIAR_NOTIF2);
                //intent.putExtra("PARAMETRO1", "1212");

                MyIntentService.startActionBaz(MainActivity.this,"HOLA 1","BROADCAST 1");
                //sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(br);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CANAL1";
            String description = "MI CANAL 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("999", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
            getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
