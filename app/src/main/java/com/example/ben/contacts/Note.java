package com.example.ben.contacts;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "content")
    private String mNoteContent;

    public Note(@NonNull String noteContent) {
        this.mNoteContent = noteContent;
    }

    public String getNoteContent() {
        return this.mNoteContent;
    }
}