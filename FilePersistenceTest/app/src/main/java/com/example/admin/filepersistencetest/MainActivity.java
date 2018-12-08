package com.example.admin.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button btnSync;
    private TextView dataText;

    //TextUtils.isEmpty方法很好用可以同时判断两种空值
    //每次重新安装app时应判断是否存在要读写的文件，若无，则生成之
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        btnSync = findViewById(R.id.btn_sync);
        dataText = findViewById(R.id.data_text);
        File f = new File("/data/data/com.example.admin.filepersistencetest/files/data");
        if (!f.exists()) {
            save("");
        }
        String textContent = load();
        if (!TextUtils.isEmpty(textContent)) {
            dataText.setText(textContent);
        }

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    save(editText.getText().toString());
                    dataText.setText(load());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }

    //读取文件中的字符串
    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content.toString();
    }


    //openFileOutput提供文件流，其中两个参数分别是文件名和读写模式，文件流作为参数提供给OutputStreamWriter,再作为参数传递给BufferedWriter
    private void save(String inputText) {
        FileOutputStream outStream = null;
        BufferedWriter writer = null;
        try {
            outStream = openFileOutput("data", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(outStream));
            writer.write(inputText + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //无论try中代码如何finally的代码都会被执行
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
