package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class QuizProgressTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityScenarioRule
            = new ActivityScenarioRule<>(QuizActivity.class);

    @Before
    public void startQuiz() {
        onView(withId(R.id.submitBtn)).perform(click());
    }

    @Test
    public void progressQuiz() {

    }

}
