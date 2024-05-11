package com.example.taskminder;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    NavController navController ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        HomePage homefrag= new HomePage();
        TaskPage taskfrag= new TaskPage();
        CalendarPage calendarfrag= new CalendarPage();
        ProfilePage proffrag= new ProfilePage();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homefrag).commit();
      bottomNavigationView = findViewById(R.id.bottom_navigation_view);
      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              if(menuItem.getItemId() == R.id.homepage){
                  getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homefrag).commit();
              } else if (menuItem.getItemId() == R.id.homepage) {
                  getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homefrag).commit();

              }
              else if (menuItem.getItemId() == R.id.taskpage) {
                  getSupportFragmentManager().beginTransaction().replace(R.id.main_content, taskfrag).commit();

              }
              else if (menuItem.getItemId() == R.id.calendarpage) {
                  getSupportFragmentManager().beginTransaction().replace(R.id.main_content, calendarfrag).commit();

              }
              else if (menuItem.getItemId() == R.id.profilepage) {
                  getSupportFragmentManager().beginTransaction().replace(R.id.main_content, proffrag).commit();

              }
              return true;
          }
      });

    }
}