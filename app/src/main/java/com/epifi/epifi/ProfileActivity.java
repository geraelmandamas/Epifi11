package com.epifi.epifi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.epifi.epifi.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.jetbrains.annotations.Nullable;

public class ProfileActivity extends AppCompatActivity {
    //BottomNavigation
    private Context  mcontext = ProfileActivity.this;
    private static final int Activity_NUM = 4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

