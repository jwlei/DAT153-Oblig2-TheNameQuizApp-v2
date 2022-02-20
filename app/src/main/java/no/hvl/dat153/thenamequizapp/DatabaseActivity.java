package no.hvl.dat153.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatabaseActivity extends AppCompatActivity {

    Database database = Database.getInstance();

    Button sort;
    Button addEntry;
    Button delete;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Database database = Database.getInstance();


        sort = findViewById(R.id.buttonSort);
        addEntry = findViewById(R.id.buttonAddEntry);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

    }
}