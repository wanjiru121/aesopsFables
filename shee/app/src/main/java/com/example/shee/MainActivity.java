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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shee.adaptors.NotesAdapter;
import com.example.shee.database.DatabaseHelper;
import com.example.shee.database.Note;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
//    TextView tvhello;
//    Button btnViewNoteActivity;
      ListView listViewNames;
    List<Note> notesList;
    String title;
    String noteText;
    int noteId;
    int position;
    private  String NOTE_API_URL = "https://akirachixnotesapi.herokuapp.com/api/v1/notes";
    private  String TAG = "NOTES_API_RESPONSE";
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
//        displayNotes();
        //getNotes();

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
    private List<Note>getNotes(){
        notesList = new ArrayList<Note>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,NOTE_API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("id");
                        String myTitle = jsonObject.getString("title");
                        String myNoteText = jsonObject.getString("noteText");
                        Note notes = new Note(id, myTitle, myNoteText);
                        notesList.add(notes);
                    }

                    NotesAdapter notesAdapter = new NotesAdapter(getBaseContext(), 0, notesList);
                    listViewNames.setAdapter(notesAdapter);
                    listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                            Note clickedNote = notesList.get(i);
                            Intent intent = new Intent(getBaseContext(), ViewNoteActivity.class);
                            intent.putExtra("NOTE_ID", clickedNote.getId());
                            startActivity(intent);
                        }
                    });
                }
                catch (Exception exception) {
                    exception.printStackTrace();

                }
            }

        }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getLocalizedMessage());
            }
        }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
        return notesList;
    };




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
        getNotes();
//        displayNotes();
    }
}
