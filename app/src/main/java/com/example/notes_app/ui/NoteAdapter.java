package com.example.notes_app.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private String[] dataSource;

    public NoteAdapter(String[] dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notes_list, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.getTextView().setText(this.dataSource[position]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            textView = ((LinearLayout) itemView).findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
        }
    }
}
