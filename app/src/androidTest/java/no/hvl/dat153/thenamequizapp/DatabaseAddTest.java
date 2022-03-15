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

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
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

import no.hvl.dat153.thenamequizapp.activities.AddEntryActivity;
import no.hvl.dat153.thenamequizapp.activities.DatabaseActivity;
import no.hvl.dat153.thenamequizapp.roomdatabase.Person;
import no.hvl.dat153.thenamequizapp.roomdatabase.PersonDAO;
import no.hvl.dat153.thenamequizapp.roomdatabase.PersonDatabase;

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
        DatabaseActivity databaseActivity = (DatabaseActivity) getInstance();
        database = PersonDatabase.getInstance(databaseActivity);
        personDAO = database.personDAO();
    }

    /* Close the DB if test is run seperatly.
    @After
         public void closeDatabase() {
        database.close();
    }
    */


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


        // Hent bilde fra resource folder
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addEntryActivity.getImageFromResource();
            }
        });

        // Skriv in navn på eksempel person
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

        // slette entry-en slik at database ikke endres
        personDAO.Delete(personToAdd);

    }

    private Activity getInstance() {
        final Activity[] currentActivity = {null};

        // Bruker instrumentationregistry for å hente ut en en instance av aktivitet som er "Resumed" altså aktiv via activityLifeCycleMonitor
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
