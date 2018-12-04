package com.example.admin.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private NetworkChangeReceiver networkChangeReceiver;

    private LocalReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);


        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.example.admin.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);


        Button btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent("com.example.admin.broadcasttest.MY_BROADCAST");
//                sendBroadcast(intent1);
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                Intent intent = new Intent("com.example.admin.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
//                Intent intent = new Intent("com.example.admin.broadcasttest.MY_BROADCAST");
//                intent.setComponent(new ComponentName("com.example.admin.broadcasttest", "com.example.admin.broadcasttest.MyBroadcastReceiver"));
//                sendOrderedBroadcast(intent, null);
//                intent.setComponent(new ComponentName("com.example.admin.broadcasttest2", "com.example.admin.broadcasttest2.AnotherBroadcastReceiver"));
//                sendBroadcast(intent);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "可以上网！！！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "无法联网！！！", Toast.LENGTH_SHORT).show();
            }

        }
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "收到本地广播！！！", Toast.LENGTH_SHORT).show();
        }
    }
}
