package com.example.ben.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CreateNoteActivity extends AppCompatActivity {
    private static final String TAG = CreateNoteActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    public void createNote(View view) {
        String noteBody = ((EditText) findViewById(R.id.editNoteBody)).getText().toString();
        String noteContact = ((EditText) findViewById(R.id.editNoteContact)).getText().toString();
        Log.d(TAG, noteBody);
        Log.d(TAG, noteContact);
    }
}
