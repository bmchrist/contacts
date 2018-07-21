package com.example.ben.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactListRecyclerViewAdapter extends RecyclerView.Adapter<ContactListRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = CreateNoteActivity.class.getSimpleName();

    private ArrayList<NoteContact> mDataset;

    // Store a reference to the text element for displaying the contact name
    // and the "x" imageButton for removing the contact
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // This is used by bindViewHolder to get the text view element to update it
        public TextView mTextView;
        public View mRemoveButton;

        public ViewHolder(ViewGroup v) {
            super(v);
            mTextView = v.findViewById(R.id.note_contact_recycler_item_text);
            mRemoveButton = v.findViewById(R.id.note_contact_recycler_item_remove);
        }
    }

    // mDataSet will hold our official list of contacts for display and later for submitting
    public ContactListRecyclerViewAdapter(ArrayList<NoteContact> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ContactListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_contact_recycler_item_layout, parent, false);
        return new ViewHolder(v);
    }

    // Builds the listed contacts views (using note_contact_recycler_item_layout)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // When building the viewholder (the data element), get the right element for this position
        //      set the text, and set a listener to remove it
        viewHolder.mTextView.setText(mDataset.get(position).getName());

        viewHolder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataset.remove(position); // returns the NoteContact that was removed
                notifyItemRemoved(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}