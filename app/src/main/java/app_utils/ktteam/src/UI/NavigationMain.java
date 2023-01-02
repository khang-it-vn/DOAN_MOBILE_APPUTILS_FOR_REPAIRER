package app_utils.ktteam.src.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app_utils.ktteam.src.R;

public class NavigationMain extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPaperMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);

        // Ánh xạ Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);
        viewPaperMain = findViewById(R.id.viewPaperMain);

        // Set sự kiện
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        viewPaperMain.setCurrentItem(0);
                        break;
                    case R.id.active:
                        viewPaperMain.setCurrentItem(1);
                        break;
                    case R.id.profile:
                        viewPaperMain.setCurrentItem(2);

                        break;
                }
                item.setChecked(true);
                return false;
            }
        });
        //set fragment tương ứng với item navigation checked
        setUpViewPaper();
    }

    private void setUpViewPaper()
    {
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.viewPaperMain.setAdapter(viewPaperAdapter);
    }
}