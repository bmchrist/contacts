package com.example.ben.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactListAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    // Default constructor
    public ContactListAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.contactName);
        String contactName = cursor.getString( cursor.getColumnIndex( ContactsContract.Contacts.DISPLAY_NAME ) );
        textView.setText(contactName);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.contacts_autocomplete_layout, parent, false);
    }
}
//public class ContactListAdapter extends ArrayAdapter {
//
//    private static final String TAG = ContactListAdapter.class.getSimpleName();
//
//
//    private List<String> dataList;
//    private Context mContext;
//    private int itemLayout;
//
//    private ContactListAdapter.ListFilter listFilter = new ContactListAdapter.ListFilter();
//
//    public ContactListAdapter(Context context, int resource, List<String> contactsDataLst) {
//        super(context, resource, contactsDataLst);
//        dataList = contactsDataLst;
//        mContext = context;
//        itemLayout = resource;
//    }
//
//    @Override
//    public int getCount() {
//        return dataList.size();
//    }
//
//    @Override
//    public String getItem(int position) {
//        return dataList.get(position);
//    }
//
//    @Override
//    public View getView(int position, View view, @NonNull ViewGroup parent) {
//
//        if (view == null) {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.contacts_autocomplete_layout, parent, false);
//        }
//
//        TextView strName = view.findViewById(R.id.contactName);
//        Log.d(TAG+"Results"," pos:"+position+" value: " + getItem(position).toString());
//        strName.setText(getItem(position).toString()); // replace with get name instead of toString()
//
//        return view;
//    }
//
//    @NonNull
//    @Override
//    public Filter getFilter() {
//        return listFilter;
//    }
//
//    public class ListFilter extends Filter {
//        private Object lock = new Object();
//
//        @Override
//        protected FilterResults performFiltering(CharSequence prefix) {
//            FilterResults results = new FilterResults();
//
//            if (prefix == null || prefix.length() == 0) {
//                synchronized (lock) {
//                    results.values = new ArrayList<String>();
//                    results.count = 0;
//                }
//            } else {
//                final String searchStrLowerCase = prefix.toString().toLowerCase();
//
//                // todo replace with check contacts API
//                retrieveContacts();
//                List<String> matchValues = new ArrayList<>(Arrays.asList("103","222","352","419","512","100","19232"));
//
//                results.values = matchValues;
//                results.count = matchValues.size();
//            }
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            if (results.values != null) {
//                Log.d(TAG, results.values.getClass().getSimpleName());
//                dataList = (List<String>) results.values;
//            } else {
//                dataList = null;
//            }
//            if (results.count > 0) {
//                notifyDataSetChanged();
//            } else {
//                notifyDataSetInvalidated();
//            }
//        }
//
//    }
//
//    // Figure out where to put this todo
//}