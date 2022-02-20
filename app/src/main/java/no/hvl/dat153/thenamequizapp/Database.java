package no.hvl.dat153.thenamequizapp;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {

    Uri Miranda = Uri.parse("android.resource://no.hvl.dat153/drawable/miranda");
    Uri Charlie = Uri.parse("android.resource://no.hvl.dat153/drawable/charlie");
    Uri Cleopatra = Uri.parse("android.resource://no.hvl.dat153/drawable/foxxycleopatra");

    private List<Person> people;

    private static Database instance = null;


    private Database() {
        people = new ArrayList<>();
    }

    public void initDatabse() {
        getPeople().add(new Person("Finn Arne", Miranda));
        getPeople().add(new Person("Per Otto", Charlie));
        getPeople().add(new Person("Joakim", Miranda));
        getPeople().add(new Person("Roger", Miranda));
        getPeople().add(new Person("Ronni Jonni", Cleopatra));
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                instance = new Database();
            }
        }
        return instance;
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person get(int position) {
        return people.get(position);
    }
}
