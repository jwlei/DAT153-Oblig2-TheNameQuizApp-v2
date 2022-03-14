package no.hvl.dat153.thenamequizapp.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonRepository {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    private PersonDAO personDAO;
    private LiveData<List<Person>> personList;
    private LiveData<List<Person>> personListAZ;
    private LiveData<List<Person>> personListZA;

    public PersonRepository(Application application)
    {
        PersonDatabase database = PersonDatabase.getInstance(application);
        personDAO = database.personDAO();
        personList = personDAO.getAllPersons();
        personListAZ = personDAO.getAllPersonsAZ();
        personListZA = personDAO.getAllPersonsZA();
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

    public LiveData<List<Person>> getAllPersonsAZ() {
        return personListAZ;
    }

    public LiveData<List<Person>> getAllPersonsZA() {
        return personListZA;
    }


}
