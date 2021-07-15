package com.example.notes_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.R;

public class NoteFragment extends Fragment {

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        String[] data = getResources().getStringArray(R.array.nameNotes);


        initRecyclerView(recyclerView, data);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, String[] data){

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NoteAdapter noteAdapter = new NoteAdapter(data);
        recyclerView.setAdapter(noteAdapter);
    }
}
