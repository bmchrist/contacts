package com.example.ben.contacts;

public class NoteContact {
    private String mName;
    private String mLookup;

    public NoteContact(String lName, String lLookup) {
        mName = lName;
        mLookup = lLookup;
    }

    public String getName() {
        return mName;
    }

    public String getLookup() {
        return mLookup;
    }

    @Override
    public String toString(){ return getName(); }
    // if we do a set, figure out hash method todo(ben) -- probably use lookup key
}
