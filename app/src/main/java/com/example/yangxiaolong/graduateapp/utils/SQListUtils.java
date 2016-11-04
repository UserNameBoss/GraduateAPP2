package com.example.yangxiaolong.graduateapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yangxiaolong on 2016/11/4.
 */
public class SQListUtils extends SQLiteOpenHelper {

    private String SQLname;
    private static final int SQLVERSION=1;
    public SQListUtils(Context context, String name) {
        super(context, name, null,SQLVERSION);
        SQLname=name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql_createTable_atticle=new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(SQLname+"( ").append("id").append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("articleId").append(" INTEGER;");

        db.execSQL(sql_createTable_atticle.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
