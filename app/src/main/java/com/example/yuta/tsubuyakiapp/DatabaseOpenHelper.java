package com.example.yuta.tsubuyakiapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuta on 15/10/12.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    /** データベース名 */
    private static final String DB_NAME = "tsubuyaki.db";

    /** データベースのバージョン */
    private static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        /** スーパークラスのコンストラクタを呼び出す */
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /** 最初にデータベースが作られる時に呼ばれるメソッド */
        /** SQLiteDatabase:データベースの実体 */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /** データベースが更新された時に呼ばれるメソッド */

    }
}
