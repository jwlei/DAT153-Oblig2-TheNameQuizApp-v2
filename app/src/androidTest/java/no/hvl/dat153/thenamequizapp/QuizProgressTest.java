package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.app.Activity;
import android.content.Intent;
import android.widget.RadioGroup;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import junit.framework.AssertionFailedError;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

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
        // hent Person objektet som vises i bildet nå
        // og ta tak i nåværende score

        QuizActivity quizActivity = (QuizActivity) getInstance();

        Person correctPerson = quizActivity.getCorrectPerson();
        String correctName = correctPerson.getName();


        // velg radiobutton med tekst som er lik riktig person sitt navn
        RadioGroup radioGroup = quizActivity.getRadioGroup();

        //onView(withId(R.id.quizAlternative1).matches(withText(correctName))).perform()

        try {
            onView(withId(R.id.quizAlternative1)).check(matches(withText(correctName))).perform(click());
        } catch (AssertionFailedError e) {
        }

        try {
            onView(withId(R.id.quitAlternative2)).check(matches(withText(correctName))).perform(click());
        } catch (AssertionFailedError e) {

        }

        try {
            onView(withId(R.id.quizAlternative3)).check(matches(withText(correctName))).perform(click());
        } catch (AssertionFailedError e) {
        }






        // Click on submit
        onView(withId(R.id.submitBtn)).perform(click());

        onView(withId(R.id.endQuizBtn)).perform(click());

        onView(withId(R.id.showResult)).check(matches(withText("Score: 1")));

        // assert at score = score+1
    }

    @Test
    public void wrongQuestion() {
        // hent Person objektet som vises i bildet nå
        // og ta tak i nåværende score

        QuizActivity quizActivity = (QuizActivity) getInstance();

        Person correctPerson = quizActivity.getCorrectPerson();
        String correctName = correctPerson.getName();


        // velg radiobutton med tekst som er lik riktig person sitt navn
        RadioGroup radioGroup = quizActivity.getRadioGroup();

        //onView(withId(R.id.quizAlternative1).matches(withText(correctName))).perform()

        // click the wrong answer and get 0 in score
        try {
            onView(withId(R.id.quizAlternative1)).check(matches(not(withText(correctName))));
        } catch (AssertionFailedError e) {
        }

        try {
            onView(withId(R.id.quitAlternative2)).check(matches(not(withText(correctName))));
        } catch (AssertionFailedError e) {

        }


        // Click on submit
        onView(withId(R.id.submitBtn)).perform(click());

        onView(withId(R.id.endQuizBtn)).perform(click());

        onView(withId(R.id.showResult)).check(matches(withText("Score: 0")));

        // assert at score = score+1
    }

    private Activity getInstance() {
        final Activity[] currentActivity = {null};
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Collection<Activity> resumedActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                Iterator<Activity> iterator = resumedActivity.iterator();
                currentActivity[0] = iterator.next();
            }
        });
        return currentActivity[0];
    }

}