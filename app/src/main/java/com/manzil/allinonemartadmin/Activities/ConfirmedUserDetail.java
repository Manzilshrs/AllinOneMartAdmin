package com.manzil.allinonemartadmin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.R;

public class ConfirmedUserDetail extends AppCompatActivity {

    TextView pname, username, phone, address, purpose;
    Button back;

    String ppname, uusername, aadress, ppurpose, pphone;


    Context context;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_user_detail);

        pname = findViewById(R.id.pname);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        purpose = findViewById(R.id.purpose);
        back = findViewById(R.id.back);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            ppname = (String) bd.get("as");
            // pstatus = (Integer) bd.get("status");
            uusername = (String) bd.get("username");
            pphone = (String) bd.get("phone");
            aadress = (String) bd.get("address");
            ppurpose = (String) bd.get("purpose");


        }


        pname.setText(ppname);
        username.setText(uusername);
        phone.setText(pphone);
        address.setText(aadress);
        purpose.setText(ppurpose);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent=new Intent(ConfirmedUserDetail.this,MainActivity.class);
              startActivity(intent);

            }
        });

    }
}
