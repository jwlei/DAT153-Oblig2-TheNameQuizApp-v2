package no.hvl.dat153.thenamequizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddEntryActivity extends AppCompatActivity {

    Database database = Database.getInstance();

    TextView tvName;
    Button addEntry;
    ImageView image;

    int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        tvName = findViewById(R.id.editTextPersonName);
        addEntry = findViewById(R.id.buttonAddPerson);
        image = findViewById(R.id.imageViewPersonPicture);


        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = tvName.getText().toString();
                database.addPerson(name);

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {

        }
    }
}