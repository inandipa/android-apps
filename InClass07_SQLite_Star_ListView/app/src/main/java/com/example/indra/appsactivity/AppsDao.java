package com.example.indra.appsactivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra on 6/16/16.
 */
public class AppsDao {
    private SQLiteDatabase db;

    public AppsDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(AppDetails appDetails){
        ContentValues values = new ContentValues();
        values.put(AppTable.Coloum_AppName,appDetails.getApp_Title());
        values.put(AppTable.Coloum_Category,appDetails.getCategory());
        values.put(AppTable.Coloum_DeveloperName,appDetails.getDeveloper_name());
        values.put(AppTable.Coloum_ImageURL,appDetails.getSmall_photo_url());
        values.put(AppTable.Coloum_Price,appDetails.getApp_price());
        values.put(AppTable.Coloum_ReleaseDate,appDetails.getRelease_date());
        return db.insert(AppTable.TableName,null,values);
    }

    public boolean update(AppDetails appDetails){
        ContentValues values = new ContentValues();
        values.put(AppTable.Coloum_AppName,appDetails.getApp_Title());
        values.put(AppTable.Coloum_Category,appDetails.getCategory());
        values.put(AppTable.Coloum_DeveloperName,appDetails.getDeveloper_name());
        values.put(AppTable.Coloum_ImageURL,appDetails.getSmall_photo_url());
        values.put(AppTable.Coloum_Price,appDetails.getApp_price());
        values.put(AppTable.Coloum_ReleaseDate,appDetails.getRelease_date());
       return  db.update(AppTable.TableName,values,AppTable.Coloum_AppName+"=?",new String[]{appDetails.getApp_Title()+""})>0;
    }

    public boolean delete(AppDetails appDetails){
        return  db.delete(AppTable.TableName,AppTable.Coloum_AppName+"=?",new String[]{appDetails.getApp_Title()})>0;

    }

    public AppDetails get(String s){
        AppDetails note = null;
       Cursor c= db.query(true,AppTable.TableName,new String[]{AppTable.Coloum_AppName,AppTable.Coloum_DeveloperName,AppTable.Coloum_ReleaseDate,AppTable.Coloum_Price,AppTable.Coloum_Category,AppTable.Coloum_ImageURL},
                AppTable.Coloum_AppName+"=?",new String[]{s},null,null,null,null);

        if(c!=null && c.moveToFirst()){
            note = getNoteFromCursor(c);
            if(! c.isClosed()){
                c.close();
            }
        }


        return note;
    }

    public List<AppDetails> getAll(){

        List<AppDetails> notes = new ArrayList<AppDetails>();
        Cursor c= db.query(true,AppTable.TableName,new String[]{AppTable.Coloum_AppName,AppTable.Coloum_DeveloperName,AppTable.Coloum_ReleaseDate,AppTable.Coloum_Price,AppTable.Coloum_Category,AppTable.Coloum_ImageURL},
                null,null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do {
                AppDetails note = getNoteFromCursor(c);
                if (note != null) {
                    notes.add(note);
                }
            }while (c.moveToNext());

            if(! c.isClosed()){
                c.close();
            }
        }


        return notes;
    }

    public AppDetails getNoteFromCursor(Cursor c){
        AppDetails note = null;
            if(c!= null){
                note = new AppDetails();
                 note.setApp_Title(c.getString(0));
                note.setDeveloper_name(c.getString(1));
                note.setRelease_date(c.getString(2 ));
                note.setApp_price(c.getString(3));
                note.setCategory(c.getString(4 ));
                note.setSmall_photo_url(c.getString(5));

            }
        return note;
    }
}
