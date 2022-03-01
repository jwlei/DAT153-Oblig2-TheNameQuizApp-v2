package no.hvl.dat153.thenamequizapp;

import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
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
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> {

    private static final String TAG = "RecyclerAdapter";
//    private final Database database = Database.getInstance();
    private List<Person> personList = new ArrayList<>();

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    public RecyclerAdapter() {
        //Log.d(TAG, "RecyclerAdapter: People in database: " + database.getPeople().size());
        Log.d(TAG, "RecyclerAdapter: People in adapter collection: " + personList.size());
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
        //holder.getTextviewPerson().setText(database.get(position).getName());
        //holder.getImageView().setImageURI(database.get(position).getImage());
        Person person = personList.get(position);
        holder.textviewPerson.setText(person.getName());

        // Setting the image for ViewHolder's imageView
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(person.getImage()
                , 0,person.getImage().length));
    }

    @Override
    public int getItemCount() {
        //return database.getPeople().size();
        return personList.size();
    }

    public Person getPosition(int position)
    {
        return personList.get(position);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        // provide a reference to the view that's used
        private TextView textviewPerson;
        private ImageView imageView;
        private Button delete; // removed in favour of decoupled solution.

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewPerson = (TextView) itemView.findViewById(R.id.textViewPersonName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewImage);
            delete = (Button) itemView.findViewById(R.id.buttonDelete);

//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d(TAG, "onClick: " + getAdapterPosition());
//                    database.getPeople().remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//                }
//            });

        }
    }
}
