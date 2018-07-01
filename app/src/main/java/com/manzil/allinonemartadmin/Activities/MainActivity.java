package com.manzil.allinonemartadmin.Activities;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.manzil.allinonemartadmin.Fragments.Home_admin_Fragment;
import com.manzil.allinonemartadmin.R;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Home_admin_Fragment categoryViewFragment = new Home_admin_Fragment();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, categoryViewFragment, null).commit();
        }


    }

//    @Override
//    public void onBackPressed(){
//        Intent a = new Intent(Intent.ACTION_MAIN);
//        a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
//    }
//    @Override
//    public void onBackPressed() {
//        if(backPressedTime+2000 > System.currentTimeMillis()){
//            backToast.cancel();
//            super.onBackPressed();
//            return;
//        }
//        else {
//            backToast = Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT);
//
//            backToast.show();
//            Intent a = new Intent(Intent.ACTION_MAIN);
//       a.addCategory(Intent.CATEGORY_HOME);
//        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(a);
//        }
//        backPressedTime  = System.currentTimeMillis();
//
//    }

    Boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

            System.exit(0);

            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit",
                Toast.LENGTH_SHORT).show();


    }
}
