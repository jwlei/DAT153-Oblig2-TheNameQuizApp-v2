package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

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

import static org.junit.Assert.*;

import android.app.Activity;

import java.util.Collection;
import java.util.Iterator;

import no.hvl.dat153.thenamequizapp.activities.DatabaseActivity;
import no.hvl.dat153.thenamequizapp.roomdatabase.PersonDAO;
import no.hvl.dat153.thenamequizapp.roomdatabase.PersonDatabase;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseRemoveTest {

    private static final String TAG = "DatabaseRemoveTest";
    private PersonDatabase database;
    private PersonDAO personDAO;

    //ActivityTestRule is deprecated but could not get a handle for the application activity
    //from ActivityScenarioRule.
    @Rule
    //public ActivityScenarioRule<DatabaseActivity> activityScenarioRule
    //        = new ActivityScenarioRule<>(DatabaseActivity.class);
    public ActivityScenarioRule<DatabaseActivity> activityScenarioRule
            = new ActivityScenarioRule<>(DatabaseActivity.class);

    @Before
    public void setupDatabase() {
        DatabaseActivity databaseActivity = (DatabaseActivity) getInstance();
        database = PersonDatabase.getInstance(databaseActivity);
        personDAO = database.personDAO();
    }


    @After
    public void closeDatabase() {
        database.close();
    }


    @Test
    public void removeInPosition() throws Exception {
        int noPersons = getNoPersonsInDatabase();

        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(0, scrollTo()));

        //Perform swipe right
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(0, swipeLeft()));

        //assert number of person in database is noPersons - 1
        assertEquals(getNoPersonsInDatabase(), (noPersons-1));
    }

    private int getNoPersonsInDatabase() {
        return personDAO.getAllCount().size();
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