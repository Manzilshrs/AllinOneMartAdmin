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

public class PasalStatusDetail extends AppCompatActivity {

    TextView pname, price, phone, email, description;
    Button back;

    String ppname, pprice, pphone,eemail,ddescription;


    Context context;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasal_status_detail);

        pname = findViewById(R.id.pname);
        price = findViewById(R.id.price);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        description = findViewById(R.id.description);
        back = findViewById(R.id.back);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            ppname = (String) bd.get("as");
            // pstatus = (Integer) bd.get("status");
            pprice = (String) bd.get("price");
            pphone = (String) bd.get("phone");
            eemail = (String) bd.get("email");
            ddescription = (String) bd.get("description");


        }


        pname.setText(ppname);
        price.setText(pprice);
        phone.setText(pphone);
        email.setText(eemail);
        description.setText(ddescription);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PasalStatusDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
