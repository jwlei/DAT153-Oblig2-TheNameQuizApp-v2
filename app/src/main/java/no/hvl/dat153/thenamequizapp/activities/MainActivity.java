package no.hvl.dat153.thenamequizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import no.hvl.dat153.thenamequizapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button database,take_quiz;
    private Intent intent;

    @Override
    public void onClick(View view) {

        Log.d(TAG, "onClick-Button clicked: " + view.getResources().getResourceEntryName(view.getId()));

        switch(view.getId()){
            case R.id.buttonDatabase:
                intent = new Intent(this, DatabaseActivity.class);

                startActivity(intent);
                break;
            case R.id.buttonQuiz:
                intent = new Intent(this, QuizActivity.class);

                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        take_quiz = findViewById(R.id.buttonQuiz);
        take_quiz.setOnClickListener(this);
        database = findViewById(R.id.buttonDatabase);
        database.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
}