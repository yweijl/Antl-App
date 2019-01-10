package com.avansprojects.antl;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class StartActivity extends AppCompatActivity {

    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        setupBottomBarNav(navController);
    }

    private void setupBottomBarNav(NavController navController)
    {
        mBottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(mBottomNav, navController);
    }
}
