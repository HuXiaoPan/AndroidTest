package com.example.hupan.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = findViewById(R.id.add_data);
        Button queryData = findViewById(R.id.query_data);
        Button updateData = findViewById(R.id.update_data);
        final Button deleteData = findViewById(R.id.delete_data);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.admin.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash Of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                Toast.makeText(MainActivity.this, newId, Toast.LENGTH_SHORT).show();
            }
        });

        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.admin.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("tag111", name + '\t' + author + "\t\t\t\t\t\t\t" + pages + "\t\t\t" + price);
                    }

                    cursor.close();
                } else {
                    Toast.makeText(MainActivity.this, "游标为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.admin.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                int updateNum = getContentResolver().update(uri, values, null, null);
                Toast.makeText(MainActivity.this, updateNum + "", Toast.LENGTH_SHORT).show();

            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.admin.databasetest.provider/book/" + newId);
                int deleteNum = getContentResolver().delete(uri, null, null);
                Toast.makeText(MainActivity.this, "" + deleteNum, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
