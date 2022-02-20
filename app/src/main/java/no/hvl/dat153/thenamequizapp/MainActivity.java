package no.hvl.dat153.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button quiz;
    Button database;
    Button addEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find button
        final Button take_quiz = findViewById(R.id.buttonQuiz);
        //Create intent
        Intent quiz = new Intent(this,QuizActivity.class);
        //Set onclick
        take_quiz.setOnClickListener(v -> startActivity(quiz));


        database = findViewById(R.id.buttonDatabase);
        addEntry = findViewById(R.id.buttonAddEntryMain);




        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });
    }
}