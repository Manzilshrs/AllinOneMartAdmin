package com.manzil.allinonemartadmin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.Model_UpdatePasal;
import com.manzil.allinonemartadmin.Model.UpdateStatus;
import com.manzil.allinonemartadmin.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetail extends AppCompatActivity {
    TextView pname, username, phone, address, purpose;
    Button confirm;

    String ppname, uusername, aadress, ppurpose, pphone;


    Context context;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        pname = findViewById(R.id.pname);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        purpose = findViewById(R.id.purpose);
        confirm = findViewById(R.id.confirm);
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

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updatesatus(ppname);
                Intent intent=new Intent(UserDetail.this,MainActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }
    public void updatesatus(String name)
    {
        Call<UpdateStatus> call = apiInterface.updatestatus(name);
        call.enqueue(new Callback<UpdateStatus>() {
            @Override
            public void onResponse(Call<UpdateStatus> call, Response<UpdateStatus> response) {
                Toast.makeText(UserDetail.this, "response value:"+response.body().getResponse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UpdateStatus> call, Throwable t) {

            }
        });

    }
}
