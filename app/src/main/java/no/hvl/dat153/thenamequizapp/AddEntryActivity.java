package no.hvl.dat153.thenamequizapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddEntryActivity extends AppCompatActivity {

    private static final String TAG = "AddEntryActivity";

    TextView tvName;
    Button addEntry;
    ImageView imageView;

    //Bitmap images
    private Bitmap chosenImage;
    private Bitmap reducedSizeImage;

    int PICK_IMAGE = 1;

    ActivityResultLauncher<Intent>activityResultLauncherForChooseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Person");
        setContentView(R.layout.activity_add_entry);

        tvName = findViewById(R.id.editTextPersonName);
        addEntry = findViewById(R.id.buttonAddPerson);
        imageView = findViewById(R.id.imageViewPersonPicture);

        registerActivityForChooseImage();

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (chosenImage == null)
                {
                    Toast.makeText(AddEntryActivity.this
                            , "Select an image for the person!"
                            , Toast.LENGTH_SHORT).show();
                }

                else
                {
                    String name = tvName.getText().toString();
                    //Cannot convert Bitmap directly, need to convert to byte[] first and also for storing.
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    reducedSizeImage = reduceImageSize(chosenImage,300);
                    reducedSizeImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                    byte [] image = outputStream.toByteArray();

                    //Sending this to DatabaseActivity, to store using the ViewModel
                    Intent intent = new Intent();
                    intent.putExtra("name",name);
                    intent.putExtra("image",image);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(AddEntryActivity.this
                        , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                        ActivityCompat.requestPermissions(AddEntryActivity.this
                        , new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncherForChooseImage.launch(intent);
                }
            }
        });
    }

    // Returns Bitmap with reduced dimensions, where maxSize is max returned width or height
    // Example: 600x400 image, maxSize passed as 300 → reduced image will be returned as, 300x200
    public Bitmap reduceImageSize(Bitmap image, int maxSize)
    {
        int width = image.getWidth();
        int height = image.getHeight();

        float ratio = (float) width / (float)height;

        if (ratio > 1)
        {
            width = maxSize;
            height = (int)(width / ratio);
        }
        else
        {
            height = maxSize;
            width = (int)(height * ratio);
        }

        return Bitmap.createScaledBitmap(image,width,height,true);
    }

    public void registerActivityForChooseImage() {
        activityResultLauncherForChooseImage
                = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        // Check on whether an image has been chosen for the new Person
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        // Image has been chosen, let us get it
                        if (resultCode == RESULT_OK && data != null) {

                            // getting image
                            try {
                                chosenImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                                imageView.setImageBitmap(chosenImage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncherForChooseImage.launch(intent);
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}