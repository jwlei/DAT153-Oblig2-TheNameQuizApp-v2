package no.hvl.dat153.thenamequizapp.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonRepository {

    // Executor service which performs the action in a background thread to avoid race-conditions
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private PersonDAO personDAO;
    private LiveData<List<Person>> personList;

    public PersonRepository(Application application)
    {
        PersonDatabase database = PersonDatabase.getInstance(application);
        personDAO = database.personDAO();
        personList = personDAO.getAllPersons();
    }


    public void insert(Person person)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                personDAO.Insert(person);
            }
        });
    }


    public void delete(Person person)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                personDAO.Delete(person);
            }
        });
    }


    public void update(Person person)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                personDAO.Update(person);
            }
        });
    }

    public LiveData<List<Person>> getAllPersons() {
        return personList;
    }



}
