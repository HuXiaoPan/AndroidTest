package com.example.admin.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLData;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyDatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        databaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,1);
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(this);
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(this);
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(this);
        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(this);
        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(this);
    }

    //CURD 两种方法 我喜欢SQL语句，干啥活说啥语言
    @Override
    public void onClick(View view) {
        SQLiteDatabase db;
        ContentValues values;
        switch (view.getId()) {
            case R.id.create_database:
                databaseHelper.getWritableDatabase();
                break;
            case R.id.add_data:
                db = databaseHelper.getWritableDatabase();

//                db.execSQL("insert into Book (name, author ,pages ,price) values (?,?,?,?)",new String[] {"The Da Vinci Code","Dan Brown" ,"454","16.96"});

                values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);
                break;
            case R.id.update_data:
                db = databaseHelper.getWritableDatabase();

//                db.execSQL(" update Book set price = ? where name = ?",new String[] {"10.99", "The Da Vinci Code"});

                values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name=?", new String[]{"The Da Vinci Code"});
                break;
            case R.id.delete_data:
                db = databaseHelper.getWritableDatabase();

//                db.execSQL("delete from Book where pages > ?", new String[] {"500"});

                db.delete("Book", "pages>?", new String[]{"500"});
                break;
            case R.id.query_data:
                db = databaseHelper.getWritableDatabase();

//                db.rawQuery("select * from Book", null);

                Cursor cursor = db.query("book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Toast.makeText(this, MessageFormat.format("\n书名：{0}\n作者：{1}\n页数：{2}\n价格：{3}", name, author, pages, price), Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            default:
                break;
        }
    }
}
