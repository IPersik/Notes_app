package com.example.notes_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.ImageNotes;
import com.example.notes_app.MainActivity;
import com.example.notes_app.R;
import com.example.notes_app.data.NoteSource;
import com.example.notes_app.data.NoteSourceImpl;

public class NoteFragment extends Fragment {

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        //String[] data = getResources().getStringArray(R.array.nameNotes);
        //initRecyclerView(recyclerView, data);
        initRecyclerView(recyclerView);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView){
        NoteSourceImpl data = new NoteSourceImpl(getResources());
        data.init();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NoteAdapter noteAdapter = new NoteAdapter(data);
        recyclerView.setAdapter(noteAdapter);
    }

    //перенесено
    /*private void createTextViewList(View view) {
        LinearLayout linearView = (LinearLayout) view;
        NoteSourceImpl notes = new NoteSourceImpl(getResources());
        notes.init();
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < notes.size(); i++) {
            String note = notes[i];
            View item = layoutInflater.inflate(R.layout.fragment_notes_list, linearView, false);
            TextView textView = item.findViewById(R.id.textView);

            textView.setText(note);
            textView.setTextSize(30);
            linearView.addView(item);
            final int finalI = i;
           textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).currentNote = new ImageNotes(finalI);
                    //showNoteDescription(((MainActivity)getActivity()).currentNote);
                }
            });
        } */

}
