package com.manzil.allinonemartadmin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.DataManager.Service;
import com.manzil.allinonemartadmin.Model.ListofCategory;
import com.manzil.allinonemartadmin.Model.ListofStatus;
import com.manzil.allinonemartadmin.Model.Model_UpdatePasal;
import com.manzil.allinonemartadmin.R;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasal extends AppCompatActivity implements Service.GetJobCallback {
    EditText  pasalPhone, pasalEmail, pasalPrice, pasalDesc;
    MaterialSpinner pasalStatus;

    String pname, pemail, pphone, pprice, pdesc;
    Integer pstatus_id,pc_id;

    Service service;

    Button update, cancel;

    Context context;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pasal);

       // pasalName = findViewById(R.id.p_no);
        pasalStatus = findViewById(R.id.status);
        pasalEmail = findViewById(R.id.email);
        pasalPhone = findViewById(R.id.phone);
        pasalPrice = findViewById(R.id.price);
        pasalDesc = findViewById(R.id.description);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        service = new Service();
        service.getStatusList(this);

        update = findViewById(R.id.update);
//        cancel = findViewById(R.id.cancel);

        pasalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pstatus_id=-1;
                pstatus_id = pasalStatus.getSelectedItemPosition();
                if (pstatus_id == 0) {
                    pasalStatus.setError("Please Select Category of Pasal.....");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            pname = (String) bd.get("as");
            // pstatus = (Integer) bd.get("status");
            pc_id=(Integer) bd.get("c_id");
            pemail = (String) bd.get("email");
            pphone = (String) bd.get("phone");
            pprice = (String) bd.get("price");
            pdesc = (String) bd.get("description");


        }


        //pasalName.setText(pname);
        pasalPhone.setText(pphone);
        pasalEmail.setText(pemail);
        pasalPrice.setText(pprice);
        pasalDesc.setText(pdesc);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo(pname);
                Intent intent=new Intent(UpdatePasal.this,MainActivity.class);
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                finish();
                startActivity(intent);

            }
        });

    }

    public void updateInfo(String name) {

        //String pName = pasalName.getText().toString().trim();
        String phoneNo = pasalPhone.getText().toString().trim();
        String emailAdd = pasalEmail.getText().toString().trim();
        String priceTag = pasalPrice.getText().toString().trim();
        String desc1 = pasalDesc.getText().toString().trim();
        Integer status_id = 2;
        Integer c_id=2;

        Call<Model_UpdatePasal> call = apiInterface.updatePasal(name,c_id,pstatus_id,desc1,priceTag, phoneNo, emailAdd );
        call.enqueue(new Callback<Model_UpdatePasal>() {
            @Override
            public void onResponse(Call<Model_UpdatePasal> call, Response<Model_UpdatePasal> response) {
                Toast.makeText(UpdatePasal.this, "response value:"+response.body().getResponse(), Toast.LENGTH_SHORT).show();
//                if(response.body().getResponse().equals("ok"))
//                {
//                    Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
//                    finish();
//                    }
//
//                else if (response.body().getResponse().equals("failed"))
//                {
//                    Toast.makeText(context, "Updated unsuccessful", Toast.LENGTH_SHORT).show();
//                }
//               else if (response.body().getResponse().equals("missing"))
//                {
//                    Toast.makeText(context, "Missing Field", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(UpdatePasal.this, "Updated", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onFailure(Call<Model_UpdatePasal> call, Throwable t) {
                Toast.makeText(UpdatePasal.this, "On failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccessCategory(List<ListofCategory> listofCategory) {

    }

    @Override
    public void onSuccessStatus(List<ListofStatus> listofStatus) {
        pasalStatus.setAdapter(new ArrayAdapter<>(this, R.
                layout.spinner_layout, R.id.textView ,listofStatus));
    }

    @Override
    public void onError(String e) {

    }
}

