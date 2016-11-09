package com.mohan.internal.mvpexample.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mohan on 6/10/16.
 */

public class Dbclass extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final int DB_VERSION = 159;
    private static final String DB_NAME = "mvp.db";


    public void initialize() {
        db = this.getWritableDatabase();
    }

    private static Dbclass dbInstance;

    public static synchronized Dbclass getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new Dbclass(context);
            dbInstance.initialize();
        }
        return dbInstance;
    }

    public Dbclass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * TABLE FOR PREFILL RESPONSE
     */
    public static final String TABLE_MOVIES = "movies_table";
    public static final String MOVIE_ID = "_id";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_DESC = "desc";

    public static final String CREATE_MOVIES_TABLE ="CREATE TABLE "
            + TABLE_MOVIES + " ("
            + MOVIE_ID + " INTEGER PRIMARY KEY, "
            + MOVIE_DESC + " text);";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST "+TABLE_MOVIES);
    }
}
