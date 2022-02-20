package no.hvl.dat153.thenamequizapp;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Person> people;

    private static Database instance = null;

    private Database() {
        people = new ArrayList<>();
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
