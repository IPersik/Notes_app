package com.example.notes_app.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes_app.MainActivity;
import com.example.notes_app.Navigation;
import com.example.notes_app.R;
import com.example.notes_app.data.NoteData;
import com.example.notes_app.data.NoteSource;
import com.example.notes_app.data.NoteSourceFirebase;
import com.example.notes_app.data.NoteSourceIResponseImpl;
import com.example.notes_app.data.NoteSourceResponse;
import com.example.notes_app.observe.Publisher;

public class NoteFragment extends Fragment {

    private static final int MY_DEFAULT_DURATION = 1000;
    private RecyclerView recyclerView;
    private NoteSourceIResponseImpl data;
    private NoteAdapter noteAdapter;
    private Navigation navigation;
    private Publisher publisher;
    private boolean moveToFirstPosition;

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);
        initView(view);
        setHasOptionsMenu(true);
        //recyclerView = view.findViewById(R.id.recycler_view_lines);
        data = (NoteSourceIResponseImpl) new NoteSourceFirebase().init(new NoteSourceResponse() {
            @Override
            public void initialized(NoteSource noteSource) {
                //noteAdapter.notifyDataSetChanged();
            }
        });
        noteAdapter.setDataSource(data);
        //data.init();
        //
        //recyclerView.setAdapter(noteAdapter);
        //setHasOptionsMenu(true);
        return view;
    }
    private void initRecyclerView(){

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        noteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);

        if (moveToFirstPosition && data.size() > 0){
            recyclerView.scrollToPosition(0);
            moveToFirstPosition = false;
        }

        // Установим слушателя

        noteAdapter = new NoteAdapter(this); //добавлен фрагмент
        noteAdapter.SetOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //new
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity)context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }
    //

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
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

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_lines);
    }



}
