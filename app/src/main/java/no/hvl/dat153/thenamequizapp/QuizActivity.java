package no.hvl.dat153.thenamequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private String ans;
    private int score;
    private int attempts;
    private boolean quizRunning = false;

    private RadioButton radioButton;
    private List<Person> person;
    private String previousAns;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        person = Database.getInstance().getPeople();

        initQuiz();

        final Button endQuizBtn = findViewById(R.id.endQuizBtn);
        endQuizBtn.setOnClickListener(view -> {
            quizRunning = true;
            initQuiz();
        });
    }

    private void initQuiz() {
        Random rand = new Random();

        if (quizRunning) {
            Intent result = new Intent(this, ResultActivity.class);
            result.putExtra("score", String.valueOf(score));
            result.putExtra("attempts", String.valueOf(attempts));
            startActivity(result);
        } else {
            Button btn1 = findViewById(R.id.quizAlternative1);
            Button btn2 = findViewById(R.id.quitAlternative2);
            Button btn3 = findViewById(R.id.quizAlternative3);

            List<Button> buttonList = Arrays.asList(btn1, btn2, btn3);
            Collections.shuffle(buttonList);

            int a = rand.nextInt(person.size());
            int b = rand.nextInt(person.size());
            int c = rand.nextInt(person.size());

            // Set Alternative A
            ans = person.get(a).getName();
            while (ans.equals(previousAns)) {
                a = rand.nextInt(person.size());
                ans = person.get(a).getName();
            }
            previousAns = ans;
            buttonList.get(0).setText(ans);

            // Set Alternative B
            String wrongAlternative1 = person.get(b).getName();
            while (ans.equals(wrongAlternative1)) {
                b = rand.nextInt(person.size());
                wrongAlternative1 = person.get(b).getName();
            }
            String nameB = person.get(b).getName();
            buttonList.get(1).setText(nameB);

            // Set Alternative C
            String wrongAlternative2 = person.get(c).getName();
            while (ans.equals(wrongAlternative2) || nameB.equals(wrongAlternative2)) {
                c = rand.nextInt(person.size());
                wrongAlternative2 = person.get(c).getName();
            }
            String nameC = person.get(c).getName();
            buttonList.get(2).setText(nameC);

            //Set image for correct Answer
            ImageView imageView = findViewById(R.id.guessPicture);
            imageView.setImageURI(person.get(a).getImage());


        }
    }

    @Override
    public void onClick(View view) {
        String answer = "";
        String displayScore = "";
        RadioGroup radioGroup = findViewById(R.id.radio_btns);

        int ansId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(ansId);
        if (radioButton != null) {
            attempts++;
            answer = (String) radioButton.getText();

            if (answer.equals(ans)) {
                score++;
            }

            displayScore = "Correct " + score + " after " + attempts + " tries.";
            TextView textView = findViewById(R.id.score);
            textView.setText(displayScore);
            radioGroup.clearCheck();

            // Reset screen for new question
            initQuiz();
        }
    }
}





