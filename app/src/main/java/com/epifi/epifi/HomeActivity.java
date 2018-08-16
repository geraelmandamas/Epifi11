package com.epifi.epifi;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.epifi.epifi.Utils.Anuncio;
import com.epifi.epifi.Utils.BottomNavigationViewHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

        public class HomeActivity extends AppCompatActivity {
            private static final String TAG = "HomeActivity";
            private Context mcontext = HomeActivity.this;
            private static final int Activity_NUM = 0;
            //firebase
            private FirebaseAuth mAuth;
            private FirebaseAuth.AuthStateListener mAuthListener;
            private DatabaseReference myref;
            private String userID;

            //Firebase Add
            private AdView mAdView;

            //Design
            private RecyclerView LVAnuncios;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_home);
                //firebase
                setUpFirebaseAuth();


        ImageView backArrow = (ImageView) findViewById(R.id.ImgSearch);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent imageSearch = new Intent(mcontext,SearchActivity.class);
              startActivity(imageSearch);



            }
        });





               ImageView IconChat = (ImageView) findViewById(R.id.IconChat);
               IconChat.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent chatintent = new Intent(mcontext,ChatActivity.class);
                       startActivity(chatintent);
                   }
               });

                FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.FAB1);
                FAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent o = new Intent(mcontext,CreateAddActivityActivity.class);
                        startActivity(o);

                    }
                });




                //RecyclerView
                LVAnuncios = (RecyclerView) findViewById(R.id.LVAnuncios);
                LVAnuncios.setHasFixedSize(true);
                LVAnuncios.setLayoutManager(new GridLayoutManager(this,2));
                //Firebase database

                myref = FirebaseDatabase.getInstance().getReference("anuncios");



                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        FirebaseRecyclerAdapter <Anuncio,AnuncioViewHolder> FBRA =  new FirebaseRecyclerAdapter<Anuncio, AnuncioViewHolder>(
                                Anuncio.class,
                                R.layout.anuncio_cell,
                                AnuncioViewHolder.class,
                                myref

                        ) {
                            @Override
                            protected void populateViewHolder(AnuncioViewHolder viewHolder, Anuncio model, int position) {
                                viewHolder.setClase(model.getClase());
                                viewHolder.setName(model.getName());
                                viewHolder.setLocation(model.getLocation());
                                viewHolder.setPrecio(model.getPrecio());
                                viewHolder.setImage(getApplicationContext(),model.getImage());


                            }
                        };
                        LVAnuncios.setAdapter(FBRA);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });























                //Firebase Add

                MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

                mAdView = findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);

                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Code to be executed when an ad request fails.
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }

                    @Override
                    public void onAdLeftApplication() {
                        // Code to be executed when the user has left the app.
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });





            }


    /*
    *
    * -----------------------------------------------------Firebase-----------------------------------------------------------------------------------------------------------------------
    * */




            public static class AnuncioViewHolder extends  RecyclerView.ViewHolder {
                public AnuncioViewHolder(View itemView) {
                    super(itemView);
                    View mView = itemView;


                }
                public void setClase(String tipoDeClase){
                    TextView anuncio_tipodeclase = (TextView) itemView.findViewById(R.id.TV1);
                    anuncio_tipodeclase.setText(tipoDeClase );
                }
                public void setName(String name){
                    TextView  anuncio_username = (TextView) itemView.findViewById(R.id.TV3);
                    anuncio_username.setText(name);
                }
                public void setPrecio(String precio){
                    TextView  anuncio_location = (TextView) itemView.findViewById(R.id.TV2);

                    anuncio_location.setText(precio + "$");
                }
                public void setLocation(String location){
                    TextView  anuncio_precioo = (TextView) itemView.findViewById(R.id.TV4);
                    anuncio_precioo.setText(location );
                }
                public void  setImage(Context ctx , String image){
                    ImageView anuncio_imagen = (ImageView)  itemView.findViewById(R.id.IMGV1);
                    Picasso.with(ctx).load(image).into(anuncio_imagen);

                }

      /*  public void setDescripcionn(String descripcionn){
            TextView  anuncio_descripción = (TextView) itemView.findViewById(R.id.TV1);
            anuncio_descripción.setText(descripcionn);
        }*/

            }






























            private void setUpFirebaseAuth(){
                mAuth = FirebaseAuth.getInstance();
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        checkCurrentUser(user);
                        if (user != null) {
                            // User is signed in

                            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                        } else {
                            // User is signed out
                            Log.d(TAG, "onAuthStateChanged:signed_out");
                        }


                    }
                };

            }


            @Override
            public void onStart() {
                super.onStart();
                mAuth.addAuthStateListener(mAuthListener);
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
        }
