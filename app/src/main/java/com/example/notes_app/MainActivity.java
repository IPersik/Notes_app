package com.example.notes_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_NOTE = "CurrentNote";
    public ImageNotes currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        } else {
            currentNote = new ImageNotes(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            initView();
        }
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this, new StringBuilder().append("Нажали ").append(item.getTitle()).toString(), Toast.LENGTH_SHORT).show();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обработка выбора пункта меню приложения (активити)
        int id = item.getItemId();

        switch (id) {
            case R.id.action_favorite:
                Toast.makeText(MainActivity.this, "Нажали 1 пункт меню", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(MainActivity.this, "Нажали 2 пункт меню", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_customizations:
                Toast.makeText(MainActivity.this, "Нажали 3 пункт меню", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, (Parcelable) currentNote);
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        initButtonNoteList();
        initButtonNoteBody();
    }

    private void initButtonNoteBody() {
        Button buttonNoteBody = findViewById(R.id.buttonNoteBody);
        buttonNoteBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNote != null) { // если у нас уже выбрана заметка, и нам есть что показать
                    addFragment(NoteDescriptionFragment.newInstance(currentNote));
                } else { // если нечего показать(не выбрана заметка), предупреждаем пользователя
                    Toast.makeText(getApplicationContext(), "Не выбрана ни одна заметка", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initButtonNoteList() {
        Button buttonNoteList = findViewById(R.id.buttonNoteList);
        buttonNoteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new NotesListFragment());
            }
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}