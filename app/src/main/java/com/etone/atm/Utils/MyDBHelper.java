package com.etone.atm.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private static MyDBHelper instance = null;
    private MyDBHelper(Context context){
        super(context, "expense.db", null, 1);
    }

    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static MyDBHelper getInstance(Context context){
        if (instance == null){
            instance = new MyDBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE  TABLE \"main\".\"exp\" (\"_id\" INTEGER PRIMARY KEY  NOT NULL , \"cdate\" DATETIME NOT NULL , \"info\" VARCHAR, \"amount\" INTEGER)";
        db.execSQL(sql);
        Log.d("MyDBHelper", "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
