package com.example.notes_app.data;

public interface NoteSource {
    int size();
    NoteData getNoteDate(int position);
}
