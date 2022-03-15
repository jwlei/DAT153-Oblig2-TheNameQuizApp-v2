package no.hvl.dat153.thenamequizapp.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;


import java.util.List;

import no.hvl.dat153.thenamequizapp.roomdatabase.Person;
import no.hvl.dat153.thenamequizapp.roomdatabase.PersonViewModel;
import no.hvl.dat153.thenamequizapp.R;
import no.hvl.dat153.thenamequizapp.recyclerview.RecyclerAdapter;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";

    private PersonViewModel personViewModel;

    Button sortAZ;
    Button sortZA;
    Button addEntry;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    //ActivityResultLauncher DatabaseActivity → AddEntryActivity
    private ActivityResultLauncher<Intent>activityResultLauncherForAddEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        registerActivityForAddEntry();

        sortAZ = findViewById(R.id.buttonSortAZ);
        sortZA = findViewById(R.id.buttonSortZA);
        addEntry = findViewById(R.id.buttonAddEntryDatabase);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //adapter = new RecyclerAdapter();
        RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize new occurrence of ViewModel via factory object
        // ViewModel's job is to take data → make it so it can be displayed in UI.
        personViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(PersonViewModel.class);

        personViewModel.getAllPersons().observe(DatabaseActivity.this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> personList) {
                adapter.setPersonList(personList);
            }
        });


        // ------------------ Buttons on-click ----------------
        //Sort AZ ON-CLICK
        sortAZ.findViewById(R.id.buttonSortAZ);
        sortAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button click, sort AZ");
                adapter.sortPersonListAZ();
            }
        });


        //Sort ZA ON-CLICK
        sortZA.findViewById(R.id.buttonSortZA);
        sortZA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "button click, sort ZA");
                adapter.sortPersonListZA();
            }
        });

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, AddEntryActivity.class);
                //startActivity(intent);

                //activity result launcher, because we are expecting some data back!!!
                activityResultLauncherForAddEntry.launch(intent);
            }
        });

        // Swipe to delete helper
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                personViewModel.delete(adapter.getPersonInPosition(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(recyclerView);
    }

    private void registerActivityForAddEntry() {

        activityResultLauncherForAddEntry
                = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        // Take the Person's image and name which the user picked in the AddEntryActivity,
                        // and add it to the database.
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if (resultCode == RESULT_OK && data != null) {

                            //get the name and image data.
                            String name = data.getStringExtra("name");
                            byte[] image = data.getByteArrayExtra("image");

                            //we create a new Person object, and use ViewModel to store.
                            Person person = new Person(name,image);
                            personViewModel.insert(person);
                        }
                    }
                });
    }
}
