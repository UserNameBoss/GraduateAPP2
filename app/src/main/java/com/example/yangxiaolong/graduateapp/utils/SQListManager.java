package com.example.yangxiaolong.graduateapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yangxiaolong on 2016/11/4.
 */
public class SQListManager {
    private String SQLName;
    private SQListUtils sqListUtils;
    private SQLiteDatabase db;

    public  SQListManager(Context context,String SQLName) {
        this.SQLName = SQLName;
        sqListUtils=new SQListUtils(context,SQLName);
        db=sqListUtils.getReadableDatabase();
    }

    public  void instets(String SQLName, ContentValues contentValues){
       long i= db.insert(SQLName,"articleId",contentValues);
        if(i>-1){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    public boolean search(int articleId){
        Cursor cursor=db.query(SQLName,new String[]{"id","articleId"},null,null,null,null,null);
        while (cursor.moveToNext()){
            if(articleId==cursor.getInt(cursor.getColumnIndex("articleId"))){
                return true;
            }
        }
        return false;
    }
}
