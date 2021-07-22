package com.example.notes_app.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.ImageNotes;
import com.example.notes_app.MainActivity;
import com.example.notes_app.R;
import com.example.notes_app.data.NoteData;
import com.example.notes_app.data.NoteSource;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private NoteSource dataSource;

    public NoteAdapter(NoteSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notes_list, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.setData(dataSource.getNoteDate(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public NoteViewHolder(View itemView) {
            super(itemView);
            title = ((LinearLayout) itemView).findViewById(R.id.textView);
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT);
                }
            };
        }

        private void setData(NoteData noteData){
            title.setText(noteData.getTitle());
        }
    }
}
