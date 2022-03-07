package no.hvl.dat153.thenamequizapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class QuizActivity extends AppCompatActivity {


    private static final String TAG = "QuizActivity";

    //Quiz variables
    private String answer;
    private static String correctAnswer = "";
    private String displayScore;
    private int noQuestion;
    private int correctAnswers;

    //The ViewModel
    private PersonViewModel personViewModel;

    //The components
    private TextView score;
    private Button submitButton,endQuizButton;
    private ImageView quizImage;
    private RadioGroup radioGroup;
    private static RadioButton radioButtonA;
    private static RadioButton radioButtonB;
    private static RadioButton radioButtonC;
    private RadioButton answeredButton;

    private List<Person> randomPersonsList;
    private Person correctPerson;
    public static List<Person> allOptionsList;
    public static ArrayList<Person> buttonIds = new ArrayList<>();

    HashSet<Person> mixChoices = new HashSet<>();

    private boolean quizFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        Log.d(TAG, "onCreate");

        quizImage = findViewById(R.id.guessPicture);
        radioGroup = findViewById(R.id.radio_btns);
        radioButtonA = findViewById(R.id.quizAlternative1);
        radioButtonB = findViewById(R.id.quitAlternative2);
        radioButtonC = findViewById(R.id.quizAlternative3);



        submitButton = findViewById(R.id.submitBtn);
        endQuizButton = findViewById(R.id.endQuizBtn);
        score = findViewById(R.id.score);

        // Get ViewModel handle
        personViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(PersonViewModel.class);



        if (randomPersonsList == null) {
            populateRandomPersonsList();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "submit button");
                Log.d(TAG, "quizFlag: " +quizFlag);

                int ansId = radioGroup.getCheckedRadioButtonId();

                quizFlag = (noQuestion >= 1 && noQuestion < randomPersonsList.size()) ? (quizFlag = true) : (quizFlag = false);

                Log.d(TAG, String.valueOf(noQuestion < randomPersonsList.size()));

                answer = "";

                Log.d(TAG, "ansId: " +ansId);



                if (quizFlag) {
                    answeredButton = findViewById(ansId);
                    if (answeredButton != null) {
                        answer = (String) answeredButton.getText();

                        if (answer.equals(correctAnswer)) {
                            correctAnswers = correctAnswers + 1;
                        }

                        displayScore = "Score: " +correctAnswers;
                        score.setText(displayScore);
                        radioGroup.clearCheck();

                    }
                }

                newQuestion();
            }
        });

        endQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "end quiz button");


                    Intent result = new Intent(QuizActivity.this, ResultActivity.class);
                    result.putExtra("score", String.valueOf(correctAnswers));
                    //result.putExtra("attempts", String.valueOf(attempts));
                    startActivity(result);
            }
        });

        Log.d(TAG, "oncreate_end");
    }

    private void populateRandomPersonsList() {

        personViewModel.getAllPersons().observe(QuizActivity.this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> personList) {
                Log.d(TAG, "populateRandomPersonsList, onChanged");

                randomPersonsList = personList.subList(0, personList.size());

                //add all to mixChoices list
                mixChoices.clear();
                mixChoices.addAll(randomPersonsList);

                randomPersonsList.clear();

                for (Person person : mixChoices) {
                    Log.d(TAG, person.getName());
                    randomPersonsList.add(person);
                }
            }
        });
    }

    private void newQuestion() {
        Log.d(TAG, "newQuestion()");
        Log.d(TAG, "noQuestions: " +noQuestion);
        Log.d(TAG, "randomPersonsList size: " + randomPersonsList.size());

        if (noQuestion == randomPersonsList.size()) {
            return;
        }

        correctPerson = randomPersonsList.get(noQuestion);
        correctAnswer = correctPerson.getName();

        quizImage.setImageBitmap(
        BitmapFactory.decodeByteArray(
                correctPerson.getImage()
                ,0
                ,correctPerson.getImage().length));

        List<Integer> shuffledIndices = new ArrayList<>();
        for (Person person : randomPersonsList) {
            if (randomPersonsList.indexOf(person) != noQuestion) {
                shuffledIndices.add(randomPersonsList.indexOf(person));
            }
        }
        Collections.shuffle(shuffledIndices);

        Log.d(TAG, "shuffledIndices[0] = " + shuffledIndices.get(0) + " shuffledIndices[1] = " + shuffledIndices.get(1));

        mixChoices.clear();
        mixChoices.add(correctPerson);
        mixChoices.add(randomPersonsList.get(shuffledIndices.get(0)));
        mixChoices.add(randomPersonsList.get(shuffledIndices.get(1)));


        allOptionsList = new ArrayList<>();


        allOptionsList.addAll(mixChoices);
        buttonIds.addAll(mixChoices);

        radioButtonA.setText(allOptionsList.get(0).getName());
        radioButtonB.setText(allOptionsList.get(1).getName());
        radioButtonC.setText(allOptionsList.get(2).getName());



        noQuestion = (noQuestion == randomPersonsList.size()) ? (noQuestion = randomPersonsList.size()) : (noQuestion = noQuestion+1);
    }


    // WIP getting id of correct button
    public static int getCorrectAns(){

        if (allOptionsList.get(0).equals(correctAnswer)){
            return radioButtonA.getId();

        } else if (allOptionsList.get(1).equals(correctAnswer)) {
            return radioButtonB.getId();

        } else return radioButtonC.getId();
    }

}





