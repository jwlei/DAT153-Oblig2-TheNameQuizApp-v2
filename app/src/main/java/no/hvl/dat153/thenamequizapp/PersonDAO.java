package no.hvl.dat153.thenamequizapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void Insert(Person person);

    @Delete
    void Delete(Person person);

    @Update
    void Update(Person person);

    @Query("SELECT * FROM person ORDER BY person_id ASC")
    LiveData<List<Person>> getAllPersons();

}
