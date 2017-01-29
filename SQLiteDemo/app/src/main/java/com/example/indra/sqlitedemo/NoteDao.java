package com.example.indra.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra on 6/16/16.
 */
public class NoteDao {
    private SQLiteDatabase db;

    public NoteDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Note note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.Coloum_Subject,note.getSubject());
        values.put(NotesTable.Coloum_Text,note.getNote());
        return db.insert(NotesTable.TableName,null,values);
    }

    public boolean update(Note note){
        ContentValues values = new ContentValues();
        values.put(NotesTable.Coloum_Subject,note.getSubject());
        values.put(NotesTable.Coloum_Text,note.getNote());
       return  db.update(NotesTable.TableName,values,NotesTable.Coloum_Id+"=?",new String[]{note.getId()+""})>0;
    }

    public boolean delete(Note note){
        return  db.delete(NotesTable.TableName,NotesTable.Coloum_Id+"=?",new String[]{note.getId()+""})>0;

    }

    public Note get(long id){
        Note note = null;
       Cursor c= db.query(true,NotesTable.TableName,new String[]{NotesTable.Coloum_Id,NotesTable.Coloum_Subject,NotesTable.Coloum_Text},
                NotesTable.Coloum_Id+"=?",new String[]{id+""},null,null,null,null);

        if(c!=null && c.moveToFirst()){
            note = getNoteFromCursor(c);
            if(! c.isClosed()){
                c.close();
            }
        }


        return note;
    }

    public List<Note> getAll(){

        List<Note> notes = new ArrayList<Note>();
        Cursor c= db.query(true,NotesTable.TableName,new String[]{NotesTable.Coloum_Id,NotesTable.Coloum_Subject,NotesTable.Coloum_Text},
                null,null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do {
                Note note = getNoteFromCursor(c);
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

    public Note getNoteFromCursor(Cursor c){
        Note note = null;
            if(c!= null){
                note = new Note();
                 note.setId(c.getLong(0));
                note.setSubject(c.getString(1));
                note.setNote(c.getString(2 ));
            }
        return note;
    }
}
