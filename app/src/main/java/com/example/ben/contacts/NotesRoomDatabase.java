package com.example.ben.contacts;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesRoomDatabase extends RoomDatabase {
    private static NotesRoomDatabase INSTANCE;

    public static NotesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesRoomDatabase.class, "notes_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract NoteDao noteDao();
}
