package no.hvl.dat153.thenamequizapp;

import android.net.Uri;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {

    //private String name;
    //private Uri image;

    @PrimaryKey(autoGenerate = true)
    public int person_id;
    public String name;
    public byte[] image;

//    public Person(String name, Uri image) {
//        this.name = name;
//        this.image = image;
//    }
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
//    public void setName(String name) {
//        this.name = name;
//    }


}
