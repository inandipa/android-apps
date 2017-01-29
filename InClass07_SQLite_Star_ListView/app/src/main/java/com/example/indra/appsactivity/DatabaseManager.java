package com.example.indra.appsactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by indra on 6/16/16.
 */
public class DatabaseManager {
    private Context mContext;
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase db;
    private AppsDao noteDao;


    public DatabaseManager(Context mContext) {
        this.mContext = mContext;
        openHelper = new DatabaseOpenHelper(mContext);
        db = openHelper.getWritableDatabase();
        noteDao = new AppsDao(db);
    }

    public void close(){
         if(db!= null){
             db.close();
         }
    }

    public AppsDao getNoteDao(){
        return this.noteDao;
    }

    public long save(AppDetails note){
        return this.noteDao.save(note);
    }

    public boolean update(AppDetails note){
        return this.noteDao.update(note);
    }

    public boolean delete(AppDetails note){
        return this.noteDao.delete(note);
    }

    public AppDetails get(String s){
        return this.noteDao.get(s);
    }

    public List<AppDetails> getAll() {
        return this.noteDao.getAll();
    }
}
