package com.example.admin.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "CREATE Table Book (\n" +
            "    id integer primary key autoincrement,\n" +
            "    author text,\n" +
            "    price real,\n" +
            "    pages integer,\n" +
            "    name text\n" +
            ");";

    private Context mContext;


}
