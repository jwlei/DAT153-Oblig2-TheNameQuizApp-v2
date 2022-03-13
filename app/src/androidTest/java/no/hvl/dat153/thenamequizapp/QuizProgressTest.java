package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import android.app.Activity;
import android.widget.RadioGroup;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

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

        ViewInteraction button = Espresso.onView(ViewMatchers.withId(R.id.radio_btns));

        //onView(withId(R.id.quizAlternative1).matches(withText(correctName))).perform()
        //onView(withId(R.id.quizAlternative1)).check(matches(withText(is(correctName)))).perform(click());

        onView(withId(R.id.radio_btns)).check(matches(withText(is(correctName)))).perform(click());

        // Click on submit
        onView(withId(R.id.submitBtn)).perform(click());
        onView(withId(R.id.endQuizBtn)).perform(click());
        onView(withId(R.id.showResult)).check(matches(withText("Score: 1")));

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
