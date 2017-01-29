package com.example.indra.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by indra on 6/16/16.
 */
public class NotesTable {
    final static String TableName = "notes";
    final static  String Coloum_Id = "id";
    final static  String Coloum_Subject = "subject";
    final static  String Coloum_Text = "notes";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TableName+" (");
        sb.append(Coloum_Id+" integer primary key autoincrement, ");
        sb.append(Coloum_Subject+" text not null, ");
        sb.append(Coloum_Text+ " text not null);");

        db.execSQL(sb.toString());

    }
    static  public void onUpgrade(SQLiteDatabase db,int oldVersion ,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        NotesTable.onCreate(db);
    }

}
