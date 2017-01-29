package com.example.indra.appsactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by indra on 6/16/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static final String Dbname = "myFavApps.db";
    static final int version = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, Dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AppTable.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        AppTable.onUpgrade(db,oldVersion,newVersion);

    }
}
