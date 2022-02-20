package no.hvl.dat153.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;

import java.util.Comparator;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";


    Button sortAZ;
    Button sortZA;
    Button addEntry;
    Button delete;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Database database = Database.getInstance();


        sortAZ = findViewById(R.id.buttonSortAZ);
        sortZA = findViewById(R.id.buttonSortZA);
        addEntry = findViewById(R.id.buttonAddEntryDatabase);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        sortAZ.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                database.getPeople().sort(Comparator.comparing(Person::getName));

                adapter.notifyDataSetChanged();
            }
        });

        sortZA.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {

                database.getPeople().sort(Comparator.comparing(Person::getName, Comparator.reverseOrder()));

                adapter.notifyDataSetChanged();
            }
        });

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DatabaseActivity.this, AddEntryActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");

        adapter.notifyDataSetChanged();
    }
}