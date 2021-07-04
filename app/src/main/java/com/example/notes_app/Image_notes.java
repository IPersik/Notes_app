package com.example.notes_app;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Image_notes extends Fragment {

    private static final String KEY_INDEX = "index";
    private int index;

    public Image_notes() {
        // Required empty public constructor
    }

    public static Image_notes newInstance(int index) {
        Image_notes fragment = new Image_notes();
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
        ImageView imageView =  view.findViewById(R.id.image_of_notes);
        TypedArray images = (getResources().obtainTypedArray(R.array.image_note));
        imageView.setImageResource(images.getResourceId(index, -1));
        return view;
    }
}