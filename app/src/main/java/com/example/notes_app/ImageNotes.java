package com.example.notes_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ImageNotes extends Fragment {

    public static final String KEY_INDEX = "index";
    private int index;

    public ImageNotes() {
        // Required empty public constructor
    }

    public static ImageNotes newInstance(int index) {
        ImageNotes fragment = new ImageNotes();
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(KEY_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_notes, container, false);
        TextView textView = view.findViewById(R.id.text_of_notes);
        TextView dateView = view.findViewById(R.id.date_of_notes);

        String [] textNote = getResources().getStringArray(R.array.textNotes);
        String [] dateNote = getResources().getStringArray(R.array.dateNotes);
        textView.setText(textNote[index]);
        dateView.setText(dateNote[index]);
        return view;
    }
}