package no.hvl.dat153.thenamequizapp;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    Database database;

    public RecyclerAdapter(Database database) {
        Log.d(TAG, "RecyclerAdapter: People in database: " + database.getPeople().size());
        this.database = database;
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
        holder.getTextviewPerson().setText(database.get(position).getName());
        holder.getImageviewPerson().setImageURI(database.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return database.getPeople().size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        // provide a reference to the view that's used
        private final TextView textviewPerson;
        private final ImageView imageviewPerson;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            textviewPerson = (TextView) itemView.findViewById(R.id.textViewPersonName);
            imageviewPerson = (ImageView) itemView.findViewById(R.id.imageViewImage);

        }

        public TextView getTextviewPerson() {
            return textviewPerson;
        }
        public ImageView getImageviewPerson() {
            return imageviewPerson;
        }
    }
}
