package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CorrectScoreTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule = new ActivityScenarioRule<QuizActivity>(QuizActivity.class);

    @Test
    public void noQuestions(){
        onView(withId(R.id.endQuizBtn)).perform(click());

        // Check that the correct score appears when ending the quiz without any questions
        onView(withId(R.id.showResult)).check(matches(withText("Score: 0")));
    }

    @Test
    public void correctQuestion(){
      // TODO: Get correct answer from Quiz



        onView(withId(R.id.endQuizBtn)).perform(click());
        onView(withId(R.id.showResult)).check(matches(withText("Score: 1")));
    }

    @Test
    public void wrongQuestion(){
        // TODO: Get guaranteed wrong answer from Quiz


        onView(withId(R.id.endQuizBtn)).perform(click());
        onView(withId(R.id.showResult)).check(matches(withText("Score: 0")));
    }
}
