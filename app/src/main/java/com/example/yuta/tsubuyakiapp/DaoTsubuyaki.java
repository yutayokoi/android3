package com.example.yuta.tsubuyakiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by yuta on 15/10/12.
 * データベースに対しての処理をまとめたクラス
 * テーブル追加、テーブルからデータ取得、追加などの処理をまとめてある
 * OpenHelper:DBに対しての処理
 * Dao:テーブルに対しての処理
 * フィールド、メソッドともにstaticがつく、オブジェクトをつくらない
 */
public class DaoTsubuyaki {

    /** テーブル名 */
    public static String TABLE_NAME = "tsubuyaki";

    /**
     * ID(項目名)
     * _がついているのは命名規則
     */
    public static String COLUMN_ID = "_id";

    /** コメント(項目名) */
    public static String COLUMN_COMMENT = "comment";

    /** テーブルを作成する */
    public static String create() {
        return "create table " + TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement not null, " +
                COLUMN_COMMENT + " text not null" +
                ");";
    }

    /**
     * テーブルからデータをデータを取得する処理
     * 全てのデータを取得する
     */
    public static ArrayList<String> findAll(Context context) {
        /** データベースにアクセスする */
        SQLiteDatabase db = getReadableDB(context);

        /** <String> ジェネリックス この配列には文字列しか入らない */
        ArrayList<String> listTsubuyaki = new ArrayList<String>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " order by " + COLUMN_ID, null);

        /**
         * cursor → ArrayListにデータを移す
         * cuesorのデータ(テーブルのデータ)が空の時、false
         */
        if (cursor.moveToFirst()) {
            /** データを移す処理 */
            do {
                /** idが項目0番目、commentが項目1番目 */
                listTsubuyaki.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listTsubuyaki;
    }

    /**
     * テーブルにデータを追加する
     * 書き込み専用でデータベースにアクセスする
     */
    public static long insert(Context context, String comment) {
        SQLiteDatabase db = getWritableDB(context);

        ContentValues values = new ContentValues();
        values.put(COLUMN_COMMENT, comment);

        return db.insert(TABLE_NAME, null, values);
    }

    /** データベースにアクセス:読み込み専用 */
    private static SQLiteDatabase getReadableDB(Context context) {
        DatabaseOpenHelper helper = new DatabaseOpenHelper(context);
        return helper.getReadableDatabase();
    }

    /** データベースにアクセス:書き込み専用 */
    private static SQLiteDatabase getWritableDB(Context context) {
        DatabaseOpenHelper helper = new DatabaseOpenHelper(context);
        return helper.getWritableDatabase();
    }
}
