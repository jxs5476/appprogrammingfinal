package com.jxs5476.showtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jxs5476.showtracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerView view;
    ShowDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

         db= ShowDatabase.getInstance(getApplicationContext());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        return true;
    }

    public static class DataHolder {
        private static String data = "";
        public static String getData() {return data;}
        public static void setData(String data) {DataHolder.data = data;}
    }


    @Override
    public void onResume() {
        super.onResume();
        db = ShowDatabase.getInstance(getApplicationContext());
    }

    public void newShowButton(View view){
        Intent intent = new Intent(getApplicationContext(), AddShow.class);
        intent.putExtra("id", -1);
        startActivity(intent);
    }

    public void logInButton(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), LogIn.class);
        startActivity(intent);
    }
}