package no.hvl.dat153.thenamequizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddEntryActivity extends AppCompatActivity {

    private static final String TAG = "AddEntryActivity";

    Database database = Database.getInstance();

    Uri imageUri;
    TextView tvName;
    Button addEntry;
    ImageView imageView;

    int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        tvName = findViewById(R.id.editTextPersonName);
        addEntry = findViewById(R.id.buttonAddPerson);
        imageView = findViewById(R.id.imageViewPersonPicture);


        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = tvName.getText().toString();
                database.addPerson(name, imageUri);

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {

            imageUri = data.getData();
            getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            imageView.setImageURI(imageUri);
        }
    }
}