package com.example.ben.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// Open screen with "get contacts" button
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runGetContactsPermissionActivity(View view) {
        Intent intent = new Intent(this, GetContactsPermissionActivity.class);
        startActivity(intent);
    }

    public void runCreateNoteActivity(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    public void runListNotesActivity(View view) {
        Intent intent = new Intent(this, ListNotesActivity.class);
        startActivity(intent);
    }
}