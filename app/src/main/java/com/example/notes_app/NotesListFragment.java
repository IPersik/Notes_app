package com.example.notes_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesListFragment extends Fragment {

    private boolean isLandscape;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            showLand(0);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createTextViewList((LinearLayout) view);
    }

    private void createTextViewList(LinearLayout linearLayout) {
        String[] notes = getResources().getStringArray(R.array.nameNotes);
        for (int i = 0; i < notes.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(notes[i]);
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLandscape) {
                        showLand(finalI);

                    } else {
                        Intent intent = new Intent(getActivity(), PortImageNotes.class);
                        intent.putExtra(ImageNotes.KEY_INDEX, finalI);
                        startActivity(intent);
                    }
                }
            });
            textView.setTextSize(25);
            linearLayout.addView(textView);
        }


    }

    private void showLand(int index) {
        ImageNotes imageNotes = ImageNotes.newInstance(index);
        getActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container_land, imageNotes).commit();
    }

}