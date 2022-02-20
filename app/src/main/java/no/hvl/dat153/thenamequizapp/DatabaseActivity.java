package no.hvl.dat153.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DatabaseActivity extends AppCompatActivity {

    Database database = Database.getInstance();

    Button sort;
    Button addEntry;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Database database = Database.getInstance();
        database.initDatabse();


        sort = findViewById(R.id.buttonSort);
        addEntry = findViewById(R.id.buttonAddEntry);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new RecyclerAdapter(database);
        recyclerView.setAdapter(adapter);

        final Button AddEntry = findViewById(R.id.buttonAddEntry);
        Intent addNewPerson = new Intent(this, AddNewPersonActivity.class);

        AddEntry.setOnClickListener(v -> startActivity(addNewPerson));


    }
}