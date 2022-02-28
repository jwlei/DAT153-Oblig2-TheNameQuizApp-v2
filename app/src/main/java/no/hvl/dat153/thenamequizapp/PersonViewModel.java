package no.hvl.dat153.thenamequizapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private final PersonRepository repository;
    private final LiveData<List<Person>> personList;


    public PersonViewModel(@NonNull Application application) {
        super(application);

        repository = new PersonRepository(application);
        personList = repository.getAllPersons();
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
}
