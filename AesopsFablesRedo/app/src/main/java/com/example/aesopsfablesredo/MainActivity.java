package com.example.aesopsfablesredo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnFable1;
    Button btnFable2;
    Button btnFable3;
    Button btnFable4;
    Button btnFable5;
    Button btnFable6;
    Button btnFable7;
    Button btnFable8;
    Button btnFable9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnFable1 = findViewById(R.id.btnFable1);
        btnFable2 = findViewById(R.id.btnFable2);
        btnFable3 = findViewById(R.id.btnFable3);
        btnFable4 = findViewById(R.id.btnFable4);
        btnFable5 = findViewById(R.id.btnFable5);
        btnFable6 = findViewById(R.id.btnFable6);
        btnFable7 = findViewById(R.id.btnFable7);
        btnFable8 = findViewById(R.id.btnFable8);
        btnFable9 = findViewById(R.id.btnFable9);

        FloatingActionButton btnFable1 = null;
        btnFable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AndroclesFable.class));
            }
        });
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
}
