package com.example.ben.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;

public class CreateNoteActivity extends AppCompatActivity {
    private static final String TAG = CreateNoteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        // Create an adapter for searching contacts
        // Pretty sure cursor can be null here since we use setFilterQueryProvider below
        final CursorAdapter adapter = new ContactListAdapter(CreateNoteActivity.this, null, 0);

        // Set it to filter the contacts query absed on the input constraint from the text field
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) { return getContactsCursor(constraint); }
        });

        // and apply it
        AutoCompleteTextView textView = findViewById(R.id.editNoteContact);
        textView.setAdapter(adapter);
    }

    public void createNote(View view) {
        String noteBody = ((EditText) findViewById(R.id.editNoteBody)).getText().toString();
        String noteContact = ((EditText) findViewById(R.id.editNoteContact)).getText().toString();
        Log.d(TAG, noteBody);
        Log.d(TAG, noteContact);
    }
//
//    private AdapterView.OnItemClickListener onItemClickListener =
//        new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d(TAG,"Clicked item from auto completion list "  + adapterView.getItemAtPosition(i));
//            }
//        };

    private Cursor getContactsCursor(CharSequence constraint) {
        ContentResolver cr = getContentResolver();
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + constraint +"%'";
        return cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, selection, null, null);
    }

}