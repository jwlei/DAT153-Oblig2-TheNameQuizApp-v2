package no.hvl.dat153.thenamequizapp.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "person")
public class Person {

    //private String name;
    //private Uri image;

    @PrimaryKey(autoGenerate = true)
    public int person_id;
    public String name;
    public byte[] image;

    public Person(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }
    public byte[] getImage() {
        return image;
    }

//    public String getName() {
//        return name;
//    }
//
//    public Uri getImage() {
//        return image;
//    }
//
//    public void setImage(Uri image) {
//        this.image = image;
//    }
//
    public void setName(String name) {
        this.name = name;
    }

    public static Comparator<Person> PersonNameAZComparator = new Comparator<Person>() {
        @Override
        public int compare(Person person1, Person person2) {
            return person1.getName().compareTo(person2.getName());
        }
    };

    public static Comparator<Person> PersonNameZAComparator = new Comparator<Person>() {
        @Override
        public int compare(Person person1, Person person2) {
            return person2.getName().compareTo(person1.getName());
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", name='" + name + '\'' +
                '}';
    }
}
