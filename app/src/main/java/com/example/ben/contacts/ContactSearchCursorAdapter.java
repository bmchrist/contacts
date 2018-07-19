package com.example.ben.contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ContactSearchCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    public ContactSearchCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = view.findViewById(R.id.contactName);
        String contactName = cursor.getString( cursor.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME ) );
        textView.setText(contactName);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.contacts_autocomplete_layout, parent, false);
    }

    @Override
    public CharSequence convertToString(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    }
}