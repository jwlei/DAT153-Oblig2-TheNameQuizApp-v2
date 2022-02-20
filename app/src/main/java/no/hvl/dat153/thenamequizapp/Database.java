package no.hvl.dat153.thenamequizapp;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Database {

    private List<Person> people;
    private List<String> listOfNames = new ArrayList<>();

    private static Database instance = null;

    private Database() {
        people = new ArrayList<>(Arrays.asList(
                new Person("Finn Arne",
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.finn_arne)),
                new Person("Per Otto",
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.per_otto)),
                new Person("Per Helge",
                        Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.per_helge))
        ));


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

    public boolean addPerson(String name, Uri imageUri) {

        if (name != null) {
            people.add(new Person(name, imageUri));
            return true;
        } else {
            return false;
        }

    }


    public List<String> getNames() {
        Iterator<Person> iterator = people.iterator();
        while(iterator.hasNext()) {
            listOfNames.add(iterator.next().getName());
        }
        return listOfNames;
    }
}
