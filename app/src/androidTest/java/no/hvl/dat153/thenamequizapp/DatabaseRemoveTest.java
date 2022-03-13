package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Objects;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseRemoveTest {

    private static final String TAG = "DatabaseRemoveTest";
    private PersonDatabase database;
    private PersonDAO personDAO;
    //private Context context;
    private int numberPersons;


    //ActivityTestRule is deprecated but could not get a handle for the application activity
    //from ActivityScenarioRule.
    @Rule
    //public ActivityScenarioRule<DatabaseActivity> activityScenarioRule
    //        = new ActivityScenarioRule<>(DatabaseActivity.class);
    public ActivityTestRule<DatabaseActivity> mActivityRule = new ActivityTestRule<>(DatabaseActivity.class);

    @Before
    public void setupDatabase() {
        //context = ApplicationProvider.getApplicationContext();
        //database = Room.inMemoryDatabaseBuilder(context, PersonDatabase.class).build();
        database = PersonDatabase.getInstance(mActivityRule.getActivity().getApplication());
        personDAO = database.personDAO();
    }

    @After
    public void closeDatabase() {
        database.close();
    }

    @Test
    public void removeInPosition() throws Exception {

        //GET number of Person in database noPersons
//        RecyclerView recyclerView;
//        recyclerView = findViewById(R.id.recyclerView);
//        int itemCount = recyclerView.getAdapter().getItemCount();
        int noPersons = getNoPersonsInDatabase();

        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(3, scrollTo()));

        //Perform swipe right
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(3, swipeLeft()));

        //assert number of person in database is noPersons - 1
        assertEquals(getNoPersonsInDatabase(), (noPersons-1));
    }

    private int getNoPersonsInDatabase() {

        return personDAO.getAllCount().size();
    }

}