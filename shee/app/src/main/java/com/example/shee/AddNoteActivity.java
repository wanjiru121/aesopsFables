package com.example.shee;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shee.database.DatabaseHelper;
import com.example.shee.database.Note;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etNote;
    Button btnAddPhoto;
    Button btnAddVoiceNote;
    Button btnSave;
    String title;
    String noteText;
    private String NOTES_API_URL = "https://akirachixnotesapi.herokuapp.com/api/v1/notes";
    private String TAG = "NOTES_API_RESPONSE";

    private static final int CAPTURE_IMAGE_REQUEST_CODE = 161;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);
        btnAddPhoto = findViewById(R.id.btnAddPhoto);
        btnAddVoiceNote = findViewById(R.id.btnAddVoiceNote);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                noteText = etNote.getText().toString();

                postNote(title, noteText);

//                Note note = new Note(title, noteText);
//                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(), "notes", null, 1);
//                long insert = databaseHelper.insertNote(note);
//                Log.d("insertnote", "note insertion value" + insert);

//                Log.d("title",title);
//                Log.d("note",note);


            }
        });

        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            Log.d("data", bitmap.toString());
        }
    }

    private void postNote(final String title, final String noteText) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NOTES_API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    int id = jsonObject.getInt("id");
                    String myTitle = jsonObject.getString("title");
                    String myNoteText = jsonObject.getString("noteText");

                    Note note = new Note(id, myTitle, myNoteText);
                    DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(), "notes", null, 1);
                    long rows = databaseHelper.insertNote(note);
//                    if (jsonObject.getString("status").equals("true")) {
//                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
//
//                        for (int i=0; i<jsonArray.length(); i++) {
//                            JSONObject dataobj = jsonArray.getJSONObject(i);
//                            int id = dataobj.getInt("id");
//                            String myTitle = dataobj.getString("title");
//                            String myNoteText = dataobj.getString("noteText");
//                            Note note = new Note(id, myTitle, myNoteText);
//                            DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
//                            long rows = databaseHelper.insertNote(note);
//                        }
//                    }


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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", title);
                params.put("noteText", noteText);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
    }


}