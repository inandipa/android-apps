package com.example.indra.sqlitedemo;

/**
 * Created by indra on 6/16/16.
 */
public class Note {

    private long id;
    private String Subject,note;


    public Note(String subject, String note) {
        Subject = subject;
        this.note = note;
    }

    public Note() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String  toString() {
        return "Note{" +
                "id=" + id +
                ", Subject='" + Subject + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
