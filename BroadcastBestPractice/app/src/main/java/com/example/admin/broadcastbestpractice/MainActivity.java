package com.example.admin.broadcastbestpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnForceOffline = findViewById(R.id.btn_force_offline);
        btnForceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent("com.example.admin.broadcastbestpractice.FORCE_OFFLINE");
                sendBroadcast(intent);

            }
        });
    }
}
