package com.epifi.epifi;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.epifi.epifi.Utils.Anuncio;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ChatActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 1;
    private Uri uri;
    private ImageView image23;
    private StorageReference mStorageReference;
    private DatabaseReference mDatabase;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mStorageReference = FirebaseStorage.getInstance().getReference("images");
        mDatabase = FirebaseDatabase.getInstance().getReference("anuncios ");
        progressBar = (ProgressBar) findViewById(R.id.progresbarchat);


        image23 = (ImageView) findViewById(R.id.image101);
        Button btn42 = (Button) findViewById(R.id.btnchoose);
        btn42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose();

            }
        });

        Button btn44 = (Button) findViewById(R.id.btnupload);
        btn42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
    private void choose(){
        Intent chooseintent = new Intent();
        chooseintent.setType("image/*");
        chooseintent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(chooseintent,GALLERY_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == GALLERY_REQUEST && resultCode  ==  RESULT_OK && data != null && data.getData() != null){
                uri = data.getData();
                Picasso.with(this).load(uri).into(image23);
                image23.setImageURI(uri);




            }


    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime =  MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(uri));

    }
    private void  uploadFile(){
        if (uri != null){
            StorageReference filereference = mStorageReference.child( System.currentTimeMillis() + "." + getFileExtension(uri));
            filereference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                   progressBar.setProgress(0);
                                }
                            },  500 );


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChatActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                             double progress = (100.0 * taskSnapshot .getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress) ;
                        }
                    });
        }else {

            Toast.makeText(ChatActivity.this,"Failed",Toast.LENGTH_SHORT).show();


        }


    }


}
