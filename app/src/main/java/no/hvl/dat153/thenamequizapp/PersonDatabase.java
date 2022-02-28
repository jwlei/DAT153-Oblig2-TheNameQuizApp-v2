package no.hvl.dat153.thenamequizapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Person.class,version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    private static PersonDatabase instance;
    public abstract PersonDAO personDAO();

    public static synchronized PersonDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,PersonDatabase.class,"persons_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }


}
