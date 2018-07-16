package com.example.ben.contacts;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class GetContactsActivity extends AppCompatActivity {
    private static final String TAG = GetContactsActivity.class.getSimpleName();

    // permissions
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_contacts);

        // TODO(bmchrist): permission priming

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // TODO(Bmchrist): Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.e(TAG, "TODO handle");
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            retrieveContacts();
        }
    }

    public void retrieveContacts() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cur != null && cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String lookup_key = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

            Log.i(TAG, "Name: " + name + " Lookup: " + lookup_key);
        }

        if(cur!=null){
            cur.close();
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty. TODO(bmchrist): handle/test
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    retrieveContacts();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    // TODO(bmchrist): handle
                    Log.e(TAG, "TODO handle");
                }
                return;
            }
        }
    }
}