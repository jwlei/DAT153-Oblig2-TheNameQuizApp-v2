package no.hvl.dat153.thenamequizapp.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Person.class,version = 1, exportSchema = false)
public abstract class PersonDatabase extends RoomDatabase {

    private static PersonDatabase instance;
    public abstract PersonDAO personDAO();


    // Creating a single instance of a DB if it does not exist
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
