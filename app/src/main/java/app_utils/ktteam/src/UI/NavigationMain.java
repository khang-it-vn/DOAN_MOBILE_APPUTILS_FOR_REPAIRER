package app_utils.ktteam.src.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        if(viewPaperMain.getCurrentItem() == 2)
        {

            ImageView imageView = findViewById(R.id.imgProfileAvatar);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("focus success");
                    openAndRequestPermission();
                }
            });
        }
    }

    private void setUpViewPaper()
    {
        ViewPaperAdapter viewPaperAdapter = new ViewPaperAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.viewPaperMain.setAdapter(viewPaperAdapter);
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        resultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK)
            {
                Intent data = result.getData();
                if(data == null)
                {
                    return;
                }
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    ImageView imgProfileAvatar = findViewById(R.id.imgProfileAvatar);
                    imgProfileAvatar.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    private void openAndRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {
            openGallery();
        }
        else
        {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, 155);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 155)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                openGallery();
            }
        }
    }
}