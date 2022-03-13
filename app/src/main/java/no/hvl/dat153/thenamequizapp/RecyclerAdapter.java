package no.hvl.dat153.thenamequizapp;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private DatabaseActivity databaseActivity;
    private List<Person> personList = new ArrayList<>();

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    public Person getPersonInPosition(int position) {

        return personList.get(position);
    }

    public RecyclerAdapter() {
        Log.d(TAG, "RecyclerAdapter: People in adapter collection: " + personList.size());
    }
    public RecyclerAdapter(List<Person> personList, DatabaseActivity databaseActivity) {
        this.personList = personList;
        this.databaseActivity = databaseActivity;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_record, parent, false);

        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.textviewPerson.setText(person.getName());

        // Setting the image for ViewHolder's imageView
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(person.getImage()
                , 0,person.getImage().length));
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        // provide a reference to the view that's used
        private TextView textviewPerson;
        private ImageView imageView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewPerson = (TextView) itemView.findViewById(R.id.textViewPersonName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewImage);
        }
    }

    public void sortPersonListAZ() {
        Log.d(TAG, "sortPersonListAZ");
        personList.sort(Person.PersonNameAZComparator);
        // might be a need to notify or set again
        notifyDataSetChanged();
    }

    public void sortPersonListZA() {
        Log.d(TAG, "sortPersonListAZ");
        personList.sort(Person.PersonNameZAComparator);
        // might be a need to notify or set again
        notifyDataSetChanged();
    }
}
