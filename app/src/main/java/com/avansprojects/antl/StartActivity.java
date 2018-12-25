package com.avansprojects.antl;

import android.os.Bundle;
import android.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class StartActivity extends AppCompatActivity {

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
        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottom_nav, navController);
    }
}
