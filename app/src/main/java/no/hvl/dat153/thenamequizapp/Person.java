package no.hvl.dat153.thenamequizapp;

import android.net.Uri;

public class Person {

    private String name;
    private Uri image;

    public Person(String name, Uri image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
