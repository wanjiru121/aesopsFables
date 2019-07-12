package com.example.shee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shee.adaptors.NotesAdapter;
import com.example.shee.database.DatabaseHelper;
import com.example.shee.database.Note;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
//    TextView tvhello;
//    Button btnViewNoteActivity;
      ListView listViewNames;
    List<Note> notesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AddNoteActivity.class));

            }
        });
       // tvhello = (TextView) findViewById(R.id.tvhello);
//        tvhello.setText("The Quick Brown Fox Jumped Over The Lazy Dog");
//
//        btnViewNoteActivity = findViewById(R.id.btnViewNote);
//        btnViewNoteActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getBaseContext(),ViewNoteActivity.class));
//            }
//        });

        listViewNames = findViewById(R.id.listViewNames);


//        displayNames();


    }
    private void displayNotes(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
        notesList = new ArrayList<Note>();
        notesList = databaseHelper.getNotes();
        Log.d("notes","My notesList has " + notesList.size() + "notes");
        NotesAdapter notesAdapter = new NotesAdapter(getBaseContext(),0,notesList);
        listViewNames.setAdapter(notesAdapter);
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = notesList.get(position);
                Intent intent = new Intent(getBaseContext(),ViewNoteActivity.class);
                intent.putExtra("NOTE_ID",note.getId());
                startActivity(intent);

            }
        });

    }
    private void displayNames(){
        List<String> namesList = new ArrayList<String>();
        namesList.add("Rose Wanjiku");
        namesList.add("Catherine Watiri");
        namesList.add("Tina Nanzala");
        namesList.add("Hadijah Dambi");
        namesList.add("Beyonce Akinyi");
        namesList.add("Sharon Akoth");
        namesList.add("Evelyn Mueni");
        namesList.add("Mercy Cylax");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,namesList);
        listViewNames.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNotes();
    }
}
