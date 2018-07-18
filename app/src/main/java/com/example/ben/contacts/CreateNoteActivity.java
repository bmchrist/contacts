package com.example.ben.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        // Set it to filter the contacts query based on the input constraint from the text field
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) { return getContactsCursor(constraint); }
        });

        // and apply it
        // TODO(bmchrist): is there a selector that is better than text (eg drowpdown w/ search?)
        // if not, decide how to handle invalid text and make it clear it won't be used
        AutoCompleteTextView textView = findViewById(R.id.editNoteContact);
        textView.setOnItemClickListener(onItemClickListener); // todo decide which is better);
        textView.setAdapter(adapter);
    }

    public void createNote(View view) {
        String noteBody = ((EditText) findViewById(R.id.editNoteBody)).getText().toString();
        String noteContact = ((EditText) findViewById(R.id.editNoteContact)).getText().toString();
        Log.d(TAG, noteBody);
        Log.d(TAG, noteContact);
        Log.e(TAG, "TODO: implement");
    }

    // TODO(bmchrist): This feels janky - look for better options once I know more
    private AdapterView.OnItemClickListener onItemClickListener =
        new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                String name = cursor.getString( cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String lookup = cursor.getString( cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));;
                Log.d(TAG,"Clicked item from auto completion list "  + name + " lookup: " + lookup);
                // TODO(bmchrist): Store th contact somewhere
                // Clear out the entry so we can add more
                ((EditText) findViewById(R.id.editNoteContact)).setText("");

            }
        };


    private Cursor getContactsCursor(CharSequence constraint) {
        ContentResolver cr = getContentResolver();
        // TODO(bmchrist): fuzzy search
        String selection = ContactsContract.Contacts.DISPLAY_NAME+" like'%" + constraint +"%'";
        return cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, selection, null, null);
    }

}