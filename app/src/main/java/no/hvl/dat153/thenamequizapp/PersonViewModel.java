package no.hvl.dat153.thenamequizapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Comparator;
import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private final PersonRepository repository;
    private final LiveData<List<Person>> personList;
    private final LiveData<List<Person>> personListAZ;
    private final LiveData<List<Person>> personListZA;


    public PersonViewModel(@NonNull Application application) {
        super(application);

        repository = new PersonRepository(application);
        personList = repository.getAllPersons();
        personListAZ = repository.getAllPersonsAZ();
        personListZA = repository.getAllPersonsZA();
    }

    public void insert(Person person)
    {
        repository.insert(person);
    }

    public void delete(Person person)
    {
        repository.delete(person);
    }

    public void update(Person person)
    {
        repository.update(person);
    }

    public LiveData<List<Person>> getAllPersons()
    {
        return personList;
    }

    public LiveData<List<Person>> getAllPersonsAZ() { return personListAZ; }

    public LiveData<List<Person>> getAllPersonsZA() { return personListZA; }

}
