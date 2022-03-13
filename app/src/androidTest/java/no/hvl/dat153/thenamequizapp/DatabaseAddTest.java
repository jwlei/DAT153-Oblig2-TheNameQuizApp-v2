package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.graphics.Bitmap;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseAddTest {

    private PersonDatabase database;
    private PersonDAO personDAO;

    @Rule
    public ActivityScenarioRule<DatabaseActivity> activityScenarioRule
            = new ActivityScenarioRule<>(DatabaseActivity.class);

    @Before
    public void setupDatabase() {
        //context = ApplicationProvider.getApplicationContext();
        //database = Room.inMemoryDatabaseBuilder(context, PersonDatabase.class).build();
        DatabaseActivity databaseActivity = (DatabaseActivity) getInstance();
        database = PersonDatabase.getInstance(databaseActivity);
        personDAO = database.personDAO();
    }

    @After
    public void closeDatabase() {
        database.close();
    }
    //public ActivityTestRule<DatabaseActivity> mActivityRule = new ActivityTestRule<>(DatabaseActivity.class);

    @Test
    public void addNewPersonTest() throws Throwable {

        final String nameToAdd = "Tosin";
        //Kan sammenligne, gammel og ny bitmap

        // 1 skal navigere innpå legg til Person
        onView(withId(R.id.buttonAddEntryDatabase)).perform(click());

        onView(withId(R.id.buttonAddPerson)).check(matches(isDisplayed()));

        // 2 må kalle på metode for å ordne bilde
        //må få tak i Activity-en
        AddEntryActivity addEntryActivity = (AddEntryActivity) getInstance();
        //addEntryActivity.getImageFromResource();

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                addEntryActivity.getImageFromResource();

            }
        });


        onView(withId(R.id.editTextPersonName)).perform(typeText(nameToAdd));


        // Fjerne tastatur
        Espresso.closeSoftKeyboard();

        // 3 Trykke på Add Person

        onView(withId(R.id.buttonAddPerson)).perform(click());

        // Sjekke at , er tilbake i DatabaseActivity
        onView(withId(R.id.buttonAddEntryDatabase)).check(matches(isDisplayed()));

        // Søk etter Tosin i databasen
        Person personToAdd = personDAO.getPersonByName(nameToAdd);

        // forventet, faktisk
        assertEquals(nameToAdd, personToAdd.getName());

        // ta tak i databasen

        // slette entry-en
        personDAO.Delete(personToAdd);

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
