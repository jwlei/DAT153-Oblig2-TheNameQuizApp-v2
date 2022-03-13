package no.hvl.dat153.thenamequizapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.bluetooth.BluetoothManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseRemoveTest {

    @Rule
    public ActivityScenarioRule<DatabaseActivity> activityScenarioRule
            = new ActivityScenarioRule<>(DatabaseActivity.class);

    @Test
    public void removeInPosition() throws Exception {

        //GET number of Person in database noPersons
//        RecyclerView recyclerView;
//        recyclerView = findViewById(R.id.recyclerView);
//        int itemCount = recyclerView.getAdapter().getItemCount();

        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(3, scrollTo()));
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(3, swipeLeft()));
        //Perform swipe right

        //assert number of person in database is noPersons - 1


    }

}