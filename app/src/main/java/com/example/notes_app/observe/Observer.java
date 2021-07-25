package com.example.notes_app.observe;

import com.example.notes_app.data.NoteData;

public interface Observer {
    void updateNoteData(NoteData noteData);
}
