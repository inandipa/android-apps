package com.example.indra.sqlitedemo;

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
    private NoteDao noteDao;


    public DatabaseManager(Context mContext) {
        this.mContext = mContext;
        openHelper = new DatabaseOpenHelper(mContext);
        db = openHelper.getWritableDatabase();
        noteDao = new NoteDao(db);
    }

    public void close(){
         if(db!= null){
             db.close();
         }
    }

    public NoteDao getNoteDao(){
        return this.noteDao;
    }

    public long save(Note note){
        return this.noteDao.save(note);
    }

    public boolean update(Note note){
        return this.noteDao.update(note);
    }

    public boolean delete(Note note){
        return this.noteDao.delete(note);
    }

    public Note get(long id){
        return this.noteDao.get(id);
    }

    public List<Note> getAll() {
        return this.noteDao.getAll();
    }
}
