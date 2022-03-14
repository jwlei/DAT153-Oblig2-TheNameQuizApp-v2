package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import no.hvl.dat153.thenamequizapp.activities.MainActivity;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ButtonToActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<MainActivity>(
            MainActivity.class);

    @Test
    public void startActivityTest(){

        //Click the start quiz button on main activity
        //onView(withId(R.id.buttonQuiz)).perform(click());
        onView(withId(R.id.buttonQuiz)).perform(click());

        //intended(toPackage("no.hvl.dat153.thenamequizapp.activities.QuizActivity"));

        //Check that an element on the quiz activity is displaying, verifying that we have launched the the correct
        //activity, could also check for intent matching result?
        onView(withId(R.id.radio_btns)).check(matches(isDisplayed()));
    }
}

