package no.hvl.dat153.thenamequizapp;

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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private final Database database = Database.getInstance();

    public RecyclerAdapter() {
        Log.d(TAG, "RecyclerAdapter: People in database: " + database.getPeople().size());
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
        holder.getImageView().setImageURI(database.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return database.getPeople().size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        // provide a reference to the view that's used
        private final TextView textviewPerson;
        private final ImageView imageView;
        private Button delete;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);


            textviewPerson = (TextView) itemView.findViewById(R.id.textViewPersonName);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewImage);
            delete = (Button) itemView.findViewById(R.id.buttonDelete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: " + getAdapterPosition());
                    database.getPeople().remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });

        }

        public TextView getTextviewPerson() {
            return textviewPerson;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
