package com.epifi.epifi.Utils;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.epifi.epifi.CreateAddActivityActivity;
import com.epifi.epifi.HomeActivity;
import com.epifi.epifi.LikesActivity;
import com.epifi.epifi.ProfileActivity;
import com.epifi.epifi.R;
import com.epifi.epifi.SearchActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by srgera on 11/3/18.
 */

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHelper";
    public static void setupBottomNAvigationView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }
    public static void  enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ICHome:
                        Intent intent1 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.ICsearch:
                        Intent intent2 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.ICcreateADD:
                        Intent intent3 = new Intent(context, CreateAddActivityActivity.class);
                        context.startActivity(intent3);
                        break;
                    case R.id.ICHeart:
                        Intent intent4 = new Intent(context, LikesActivity.class);
                        context.startActivity(intent4);
                        break;
                    case R.id.ICProfile:
                        Intent intent5 = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent5);
                        break;
                }
                return false;
            }
        });

    }

}

