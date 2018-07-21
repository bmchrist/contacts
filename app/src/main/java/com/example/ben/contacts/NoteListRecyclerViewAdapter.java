package com.example.ben.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListRecyclerViewAdapter extends RecyclerView.Adapter<NoteListRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = CreateNoteActivity.class.getSimpleName();

    private List<Note> mNotes; // cached copy of notes

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(ViewGroup v) {
            super(v);
            mTextView = v.findViewById(R.id.note_recycler_item_text);
        }
    }

    public NoteListRecyclerViewAdapter(Context context) {
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NoteListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // todo if issues chekc code sample
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_recycler_item_layout, parent, false);
        return new ViewHolder(v);
    }

    // Builds the listed notes
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            viewHolder.mTextView.setText(current.getNoteContent());
        } else { // in case there is no data
            viewHolder.mTextView.setText("Nothing found");
        }
    }

    void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mNotes == null) {
            return 0;
        } else {
            return mNotes.size();
        }
    }
}