package com.example.ben.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;

import java.util.ArrayList;

public class CreateNoteActivity extends AppCompatActivity {
    private static final String TAG = CreateNoteActivity.class.getSimpleName();
    private ContactListRecyclerViewAdapter recyclerViewAdapter;
    private NoteViewModel mNoteViewModel;

    // todo(ben): decide if this data structure is best
    // todo(Ben): handle duplicate contacts being added - maybe use a set
    // Holds the list of selected contacts
    private ArrayList<NoteContact> noteContacts = new ArrayList<NoteContact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        // Build views and layout for displaying selected contacts
        buildSelectedContactsDisplay();

        // Build autocompletetext for choosing contacts
        buildContactSelector();
    }

    public void createNote(View view) {
        String noteContent = ((EditText) findViewById(R.id.editNoteBody)).getText().toString();
        Note note = new Note(noteContent);
        // TODO(ben): save more than just note body
        mNoteViewModel.insert(note);
    }

    private void buildContactSelector() {
        // --------------
        // Search Adapter
        // --------------
        // Create an adapter for searching contacts
        // Pretty sure cursor can be null here since we use setFilterQueryProvider below
        final CursorAdapter adapter = new ContactSearchCursorAdapter(CreateNoteActivity.this, null, 0);

        // Set it to filter the contacts query based on the input constraint from the text field
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                ContentResolver cr = getContentResolver();
                // TODO(bmchrist): fuzzy search
                String selection = ContactsContract.Contacts.DISPLAY_NAME+" like'%" + constraint +"%'";
                return cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, selection, null, null);
            }
        });

        // and apply it to the view
        // TODO(bmchrist): is there a selector/element that is better than autocompletetext (eg drowpdown w/ search?)
        //      if not, decide how to handle invalid text and make it clear it won't be used
        AutoCompleteTextView textView = findViewById(R.id.editNoteContact);
        textView.setOnItemClickListener(onItemClickListener); // todo(bmchrist): itemSelected does not work - why? (this works fine though)
        textView.setAdapter(adapter);
    }

    private ContactListRecyclerViewAdapter buildSelectedContactsDisplay() {
        RecyclerView recyclerView = findViewById(R.id.contactsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAdapter = new ContactListRecyclerViewAdapter(noteContacts);
        recyclerView.setAdapter(recyclerViewAdapter);
        return recyclerViewAdapter;
    }

    // TODO(bmchrist): This feels janky - look for better options once I know more
    private AdapterView.OnItemClickListener onItemClickListener =
        new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                NoteContact noteContact = new NoteContact(
                    cursor.getString( cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                    cursor.getString( cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY))
                );

                // Add to list of selected contacts
                noteContacts.add(noteContact);
                recyclerViewAdapter.notifyItemInserted(noteContacts.size()-1);

                // Clear out the entry so we can add more
                ((EditText) findViewById(R.id.editNoteContact)).setText("");
            }
        };
}