package com.manzil.allinonemartadmin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.manzil.allinonemartadmin.R;

public class PasalStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasal_status);

    }

    public void getavailable(View view) {
        Intent intent= new Intent(this,ListPasalStatus.class);
        intent.putExtra("type","available");
        startActivity(intent);
    }

    public void getreserved(View view) {

        Intent intent= new Intent(this,ListPasalStatus.class);
        intent.putExtra("type","reserved");
        startActivity(intent);
    }

    public void getsold(View view) {
        Intent intent= new Intent(this,ListPasalStatus.class);
        intent.putExtra("type","sold");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(PasalStatus.this,MainActivity.class);
        startActivity(i);
    }
}
