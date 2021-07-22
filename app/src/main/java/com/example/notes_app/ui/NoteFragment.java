package com.example.notes_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.ImageNotes;
import com.example.notes_app.MainActivity;
import com.example.notes_app.R;
import com.example.notes_app.data.NoteData;
import com.example.notes_app.data.NoteSource;
import com.example.notes_app.data.NoteSourceImpl;

public class NoteFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoteSourceImpl data;
    private NoteAdapter noteAdapter;

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        //initRecyclerView(recyclerView);
        data = new NoteSourceImpl(getResources());
        data.init();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(data);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT);
            }
        };
        recyclerView.setAdapter(noteAdapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                data.addNote(new NoteData("Какая-то заметка" + (data.size()+1)));
                noteAdapter.notifyItemInserted(data.size()-1);
                recyclerView.scrollToPosition(data.size()-1);
                return true;
            case R.id.action_clear:
                data.clearNote();
                noteAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    /*private void initRecyclerView(RecyclerView recyclerView){
        data = new NoteSourceImpl(getResources());
        data.init();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(data);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT);
            }
        };
        recyclerView.setAdapter(noteAdapter);
        setHasOptionsMenu(true);
    } */
}
