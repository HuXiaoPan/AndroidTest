package com.example.admin.sharedpreferencestest;

import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSave;
    private Button btnLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btn_save);
        btnLoad = findViewById(R.id.btn_load);
        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("姓名","齐小晟");
                editor.putInt("年龄",28);
                editor.putBoolean("是否已婚",false);
                editor.apply();
                break;
            case R.id.btn_load:
                SharedPreferences pref= getSharedPreferences("data",MODE_PRIVATE);
                String name = pref.getString("姓名","");
                int age = pref.getInt("年龄",0);
                boolean married = pref.getBoolean("是否已婚",true);
                Toast.makeText(this, "姓名：" + name + "\n" + "年龄：" + age + "\n" + "是否已婚：" + married + "\n", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
