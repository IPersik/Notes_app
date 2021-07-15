package com.example.notes_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class NotesListFragment extends Fragment {

    private boolean isLandscape;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createTextViewList(view);
    }

    private void createTextViewList(View view) {
        LinearLayout linearView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.nameNotes);
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int i = 0; i < notes.length; i++) {
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
                    showNoteDescription(((MainActivity)getActivity()).currentNote);
                }
            });
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            showLandNoteDescription(((MainActivity) requireActivity()).currentNote);
        }
    }

    private void showNoteDescription(ImageNotes currentNote){
        if (isLandscape) {
            showLandNoteDescription(currentNote);

        } else {
            showPortNoteDescription(currentNote);
        }
    }

    private void showPortNoteDescription(ImageNotes currentNote) {
        Toast.makeText(getActivity()," Выбрана заметка "+currentNote.getNoteName(getActivity()),Toast.LENGTH_SHORT).show();
    }

    private void showLandNoteDescription(ImageNotes currentNote) {
        NoteDescriptionFragment detail = NoteDescriptionFragment.newInstance(currentNote);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

}