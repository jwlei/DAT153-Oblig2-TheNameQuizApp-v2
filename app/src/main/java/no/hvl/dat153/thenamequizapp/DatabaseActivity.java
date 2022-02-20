package no.hvl.dat153.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        database.getPeople().add(new Person("Finn Arne"));
        database.getPeople().add(new Person("Per Otto"));
        database.getPeople().add(new Person("Joakim"));
        database.getPeople().add(new Person("Roger"));
        database.getPeople().add(new Person("Ronni Jonni"));

        sort = findViewById(R.id.buttonSort);
        addEntry = findViewById(R.id.buttonAddEntry);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new RecyclerAdapter(database);
        recyclerView.setAdapter(adapter);
    }
}