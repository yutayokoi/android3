package com.example.yuta.tsubuyakiapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * データベースのバージョン管理、オープン、テーブルの作成・更新などを行うクラス
 * Created by yuta on 2015/10/07.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    // ----------fields----------

    /** データベース名の指定 */
    private static final String DB_NAME = "tsubuyaki.db";

    /** データベースのバージョン指定 */
    private static final int DB_VERSION = 1;

    // ----------constructor----------

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // ----------methods----------

    @Override
    public void onCreate(SQLiteDatabase db) {
        // データベース初回作成時にテーブル作成
        db.execSQL(DaoTsubuyaki.create());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // データベースに変更が生じた場合は、ここに処理を記述する
    }
}