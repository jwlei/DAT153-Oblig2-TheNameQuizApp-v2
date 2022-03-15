package no.hvl.dat153.thenamequizapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import no.hvl.dat153.thenamequizapp.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String score = "";

        // Get the score through bundle extras
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            score = extras.getString("score");
        }

        String display = "Score: " +score;
        TextView textView = findViewById(R.id.showResult);
        // Set the text of textView to showing "Score: " + score
        textView.setText(display);

        // Return to start button
        final Button returnToStart = findViewById(R.id.returnToStartBtn);
        Intent MainActivity = new Intent(this, no.hvl.dat153.thenamequizapp.activities.MainActivity.class);
        returnToStart.setOnClickListener(v -> startActivity(MainActivity));
    }
}