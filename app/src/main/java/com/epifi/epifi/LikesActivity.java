package com.epifi.epifi;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.epifi.epifi.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class LikesActivity extends AppCompatActivity {
    //BottomNavigation
    private Context mcontext = LikesActivity.this;
    private static final int Activity_NUM = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);

        setupBottomNavigationView();
    }
    /*
 *
 * -----------------------------------------------------BottomNavigation-----------------------------------------------------------------------------------------------------------------------
 * */
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.BNV);
        BottomNavigationViewHelper.setupBottomNAvigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Activity_NUM);
        menuItem.setChecked(true);
    }
}
