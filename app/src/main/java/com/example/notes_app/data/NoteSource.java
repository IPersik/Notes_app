package com.example.notes_app.data;

public interface NoteSource {
    int size();
    NoteData getNoteDate(int position);

    void addNote(NoteData noteData);
    void deleteNote(int position);
    void updateNote(int position, NoteData noteData);
    void clearNote();
}
