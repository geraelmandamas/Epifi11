package com.epifi.epifi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.epifi.epifi.Utils.BottomNavigationViewHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SearchActivity extends AppCompatActivity /* implements SearchView.OnQueryTextListener, AbsListView.OnScrollListener*/ {
    //BottomNavigation
    private Context mcontext = SearchActivity.this;
    private static final int Activity_NUM = 1;
    //Firebase Add
    private AdView mAdView;
    private ProgressBar progressBarSearch;


    //Widgets
    private EditText claseSearch ,ubicacionSearch ,precioSearch;
    private  ImageView backimage,checkSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       // setupBottomNavigationView();
         setupToolbar();
         progressBarSearch = findViewById(R.id.ProgressBarSA);
         progressBarSearch.setVisibility(View.GONE);
         claseSearch = (EditText) findViewById(R.id.hintBuscarClaseSearch);
         ubicacionSearch = (EditText) findViewById(R.id.hintUbicacionSearch);
         precioSearch = (EditText) findViewById(R.id.hintPrecioSearch);
         init();







 /*
 *
 * --------------------------------------------------------------FirebaseAdd--------------------------------------------------------------------------------------------------------------
 * */
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
 * -----------------------------------------------------BottomNavigation-----------------------------------------------------------------------------------------------------------------------
 * */
   /* private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.BNV);
        BottomNavigationViewHelper.setupBottomNAvigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Activity_NUM);
        menuItem.setChecked(true);
    }

    /*
*
* -----------------------------------------------------Toolbar-----------------------------------------------------------------------------------------------------------------------
* */

   private void setupToolbar() {

       //Te devuelve a HomeActivity
         backimage = (ImageView) findViewById(R.id.imageBackSearch);
       backimage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });



    }
    /*
*
* -----------------------------------------------------init-----------------------------------------------------------------------------------------------------------------------
* */

    private void init(){
        getFilterPreferences();

         checkSearch = (ImageView) findViewById(R.id.imgcheck11);
        checkSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SearchActivity.this);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(getString(R.string.preferences_ubicacion),ubicacionSearch.getText().toString());
                editor.commit();

                editor.putString(getString(R.string.preferences_precio),precioSearch.getText().toString());
                editor.commit();
                finish();



            }

        });



    }

    private void getFilterPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ubicacion = preferences.getString(getString(R.string.preferences_ubicacion),"");
        String precio = preferences.getString(getString(R.string.preferences_precio),"");
        ubicacionSearch.setText(ubicacion);
        precioSearch.setText(precio);
    }




}
