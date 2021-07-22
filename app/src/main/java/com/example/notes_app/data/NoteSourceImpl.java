package com.example.notes_app.data;

import android.content.res.Resources;

import com.example.notes_app.R;

import java.util.ArrayList;
import java.util.List;

public class NoteSourceImpl implements NoteSource{
    private List<NoteData> noteSource;
    private Resources resources;

    public NoteSourceImpl(Resources resources){
        this.resources = resources;
        noteSource = new ArrayList<>();
    }
    public void init(){
        String[] titles = resources.getStringArray(R.array.nameNotes);
        for (int i = 0; i < titles.length; i++) {
            noteSource.add(new NoteData(titles[i]));
        }
    }

    @Override
    public int size() {
        return noteSource.size();
    }

    @Override
    public NoteData getNoteDate(int position) {
        return noteSource.get(position);
    }

    public List<NoteData> getNoteSource() {
        return noteSource;
    }

}
