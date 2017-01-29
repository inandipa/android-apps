package com.example.indra.appsactivity;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by indra on 6/16/16.
 */
public class AppTable {

    final static String TableName = "FavApps";
    final static  String Coloum_AppName= "appName";
    final static  String Coloum_DeveloperName = "developerName";
    final static  String Coloum_ReleaseDate = "releaseDate";
    final static  String Coloum_Price = "price";
    final static  String Coloum_Category = "category";
    final static  String Coloum_ImageURL = "imageURL";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TableName+" (");
        sb.append(Coloum_AppName+" text primary key , ");
        sb.append(Coloum_DeveloperName+" text not null, ");
        sb.append(Coloum_ReleaseDate+" text not null, ");
        sb.append(Coloum_Price+" text not null, ");
        sb.append(Coloum_Category+" text not null, ");
        sb.append(Coloum_ImageURL+ " text not null);");

        db.execSQL(sb.toString());

    }
    static  public void onUpgrade(SQLiteDatabase db,int oldVersion ,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        AppTable.onCreate(db);
    }

}
