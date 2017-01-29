package com.example.indra.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by indra on 6/16/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static final String Dbname = "mynotes.db";
    static final int version = 2;

    public DatabaseOpenHelper(Context context) {
        super(context, Dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        NotesTable.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        NotesTable.onUpgrade(db,oldVersion,newVersion);

    }
}
