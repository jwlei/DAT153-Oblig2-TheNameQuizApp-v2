package no.hvl.dat153.thenamequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String score = "";
        String total = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            score = extras.getString("score");
            total = extras.getString("total");
        }
        String display = score + " points after " + total + " questions!";
        TextView textView = findViewById(R.id.showResult);
        textView.setText(display);

        final Button returnToStart = findViewById(R.id.returnToStartBtn);
        Intent MainActivity = new Intent(this, MainActivity.class);
        returnToStart.setOnClickListener(v -> startActivity(MainActivity));
    }
}