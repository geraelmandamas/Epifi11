package com.epifi.epifi;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.epifi.epifi.Utils.Anuncio;
import com.epifi.epifi.Utils.BottomNavigationViewHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

public class CreateAddActivityActivity extends AppCompatActivity {
    //BottomNavigation
    private Context  mcontext = CreateAddActivityActivity.this;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private ImageView IMGV;
    private Uri uri = null;
    private ProgressBar mprogressBarCA;
    private static final  int GALLERY_REQUEST = 71;
    private FirebaseStorage storage;
    private StorageReference mStorageReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_add_activity);
        setUpFirebaseAuth();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mprogressBarCA = (ProgressBar) findViewById(R.id.ProgressBarCA);
        mprogressBarCA.setVisibility(View.GONE);


        ImageView backArrow = (ImageView) findViewById(R.id.IconBack);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        IMGV = (ImageView) findViewById(R.id.IMGADDFOTO);
        IMGV.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                choose();
        }
    });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("anuncios");
        mAuth = FirebaseAuth.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference("images");



        Button Listo = (Button) findViewById(R.id.Listo);
        Listo.setOnClickListener(new View.OnClickListener() {
            EditText TipoClase = (EditText) findViewById(R.id.TipoDeMonedaHint);
            EditText Precio = (EditText) findViewById(R.id.PrecioHint);
            EditText Username = (EditText) findViewById(R.id.userHint);
            EditText Location = (EditText) findViewById(R.id.LocationHint);
            EditText Descricion = (EditText) findViewById(R.id.DescriptionHint);



            @Override
            public void onClick(View view) {
                final String tipoClase = TipoClase.getText().toString();
                final String precio = Precio.getText().toString();

                final String username = Username.getText().toString();
                final String location = Location.getText().toString();
                final String descripcion = Descricion.getText().toString();

                if (isStringNull(tipoClase) && isStringNull(precio) && isStringNull(username) && isStringNull(location) && isStringNull(descripcion) ){
                    Toast.makeText(mcontext,"Debe rellenar todo",Toast.LENGTH_LONG).show();

                }else {
                   // mprogressBarCA.setVisibility(View.VISIBLE);
                  //  Anuncio anuncio = new Anuncio(Username.getText().toString() , TipoClase.getText().toString() ,Location.getText().toString(),  Descricion.getText().toString(), Precio.getText().toString() );
                  //  DatabaseReference newAnuncio = myRef.child("anuncios").push();
                  //  newAnuncio.setValue(anuncio);

                    uploadFile();



                    /*        Anuncio anuncio = new Anuncio(Username.getText().toString(),download.toString() , TipoClase.getText().toString() ,Location.getText().toString(),  Descricion.getText().toString(), Precio.getText().toString() );
                            DatabaseReference newAnuncio = myRef.child("anuncios").push();
                            newAnuncio.setValue(anuncio);
                            TipoClase.setText("");
                            Precio.setText("");
                            Username.setText("");
                            Location.setText("");
                            Descricion.setText("");



*/

                    }




               userID = mAuth.getCurrentUser().getUid();

                //newAnuncio.child("Username").setValue(username);
                // newAnuncio.child("Location").setValue(location);
                //newAnuncio.child("Description").setValue(descripcion);



            }
        });
    }



/*---------------------------------Firebase----------------------------------------------------*/

    private void setUpFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //Check current user
                checkCurrentUser(user);
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
                // ...
            }
        };

    }
    private boolean isStringNull(String string){
        if (string.equals("")){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }




    //Check if the "user " is signed in
    private void checkCurrentUser(FirebaseUser user){
        if (user == null ){
            Intent intent = new Intent(mcontext,LoginActivity.class);
            startActivity(intent);
        }

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
            Picasso.with(this).load(uri).into(IMGV);
            IMGV.setImageURI(uri);




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
                        EditText TipoClase = (EditText) findViewById(R.id.TipoDeMonedaHint);
                        EditText Precio = (EditText) findViewById(R.id.PrecioHint);
                        EditText Username = (EditText) findViewById(R.id.userHint);
                        EditText Location = (EditText) findViewById(R.id.LocationHint);
                        EditText Descricion = (EditText) findViewById(R.id.DescriptionHint);

                        final String tipoClase = TipoClase.getText().toString();
                        final String precio = Precio.getText().toString();

                        final String username = Username.getText().toString();
                        final String location = Location.getText().toString();
                        final String descripcion = Descricion.getText().toString();
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogressBarCA.setProgress(1);
                                    mprogressBarCA.setVisibility(View.VISIBLE);

                                }
                            },  500 );

                            Toast.makeText(mcontext,"Subido exitosamente",Toast.LENGTH_SHORT).show();
                            if (isStringNull(tipoClase) && isStringNull(precio) && isStringNull(username) && isStringNull(location) && isStringNull(descripcion) ){
                                Toast.makeText(mcontext,"Debe rellenar todo",Toast.LENGTH_LONG).show();

                            }else {
                                Anuncio anuncio = new Anuncio(Username.getText().toString(), taskSnapshot.getDownloadUrl().toString(), TipoClase.getText().toString() ,Location.getText().toString(),  Descricion.getText().toString(), Precio.getText().toString() );
                            String anuncioid = myRef.push().getKey();
                            myRef.child(anuncioid).setValue(anuncio);
                            TipoClase.setText("");
                            Precio.setText("");
                            Username.setText("");
                            Location.setText("");
                            Descricion.setText("");
                            Intent intenttoHome = new Intent(mcontext,HomeActivity.class);
                            startActivity(intenttoHome);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(mcontext,e.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot .getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                            mprogressBarCA.setProgress((int) progress) ;
                        }
                    });
        }else {

            Toast.makeText(mcontext,"Failed",Toast.LENGTH_SHORT).show();


        }


    }



}
