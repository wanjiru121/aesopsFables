package com.example.shee;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shee.database.DatabaseHelper;
import com.example.shee.database.Note;

public class EditNoteActivity extends AppCompatActivity {
    EditText etTitle2;
    EditText etNote2;
    Button btnUpdate;
    int noteId;
    String title;
    String noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getNoteId();

        etTitle2 = findViewById(R.id.etTitle2);
        etNote2 = findViewById(R.id.etNote2);
        btnUpdate = findViewById(R.id.btnUpdate);

        displayNote();



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle2.getText().toString();
                noteText = etNote2.getText().toString();
                Note note = new Note(noteId,title, noteText);
                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(), "notes", null, 1);
                databaseHelper.updateNote(note);
                finish();
            }
        });




    }

    private void getNoteId(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            noteId = extras.getInt("NOTE_ID");

        }
    }
    private void displayNote(){

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
        Note note = databaseHelper.getNoteById(noteId);
        etTitle2.setText(note.getTitle());
        etNote2.setText(note.getNoteText());
    }

}
