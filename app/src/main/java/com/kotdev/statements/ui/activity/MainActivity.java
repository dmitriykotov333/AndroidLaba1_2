package com.kotdev.statements.ui.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.kotdev.statements.R;
import com.kotdev.statements.ui.fragments.posts.FirstFragmentDirections;
import com.kotdev.statements.ui.fragments.profile.AccountFragmentDirections;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;


/**
 * Class sort array conversely
 *
 * @author Kotov Dmitriy
 * @version 1
 * @since 21.09.2018
 */
public class MainActivity extends DaggerAppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private NavController navController;

    private AppBarConfiguration appBarConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        check(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        setSupportActionBar(findViewById(R.id.toolbar));

        appBarConfig = new AppBarConfiguration.Builder(navigationView.getMenu())
                .setOpenableLayout(drawerLayout)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfig) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void check(String a, String b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(a)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{b}
                        , 1);
            }
        }
    }
}

