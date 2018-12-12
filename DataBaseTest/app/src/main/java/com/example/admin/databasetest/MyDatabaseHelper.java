package com.example.admin.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "CREATE Table Book (\n" +
            "    id integer primary key autoincrement,\n" +
            "    author text,\n" +
            "    price real,\n" +
            "    pages integer,\n" +
            "    name text\n" +
            ");";

    public static final String CREATE_CATEGORY = "CREATE Table category (\n" +
            "    id integer primary key autoincrement,\n" +
            "    category_name text,\n" +
            "    category_code integer\n" +
            ");";

    public static final String CREATE_TESTTABLE = "CREATE Table testtable (\n" +
            "    id integer primary key autoincrement,\n" +
            "    category_name text,\n" +
            "    category_code integer\n" +
            ");";

    private Context mContext;

    //factory参数允许我们查询数据时返回一个自定义的Cursor（？？？）
    public MyDatabaseHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS book");
        db.execSQL("DROP TABLE if EXISTS category");
        onCreate(db);
//        Toast.makeText(mContext, "升级数据库完成", Toast.LENGTH_LONG).show();
    }
}
