package com.manzil.allinonemartadmin.Activities;

import android.content.Context;
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
import com.manzil.allinonemartadmin.Model.Model_AddPasal;
import com.manzil.allinonemartadmin.R;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPasal extends AppCompatActivity implements Service.GetJobCallback
{
MaterialSpinner c_id,status;
Integer categoryid;
Button confirm,cancel;
EditText pasalNo,phone,email,price,description;
    Service service;

    Context context;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pasal);

        c_id=findViewById(R.id.c_id);
        status=findViewById(R.id.status);
        confirm=findViewById(R.id.confirm);
        //cancel=findViewById(R.id.cancel);

        pasalNo=findViewById(R.id.p_no);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        price=findViewById(R.id.price);
        description=findViewById(R.id.description);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        service = new Service();
        service.getCategoryList(this);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPasal();
            }
        });

        c_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryid=-1;
                categoryid = c_id.getSelectedItemPosition();
                if (categoryid == 0) {
                    c_id.setError("Please Select Category of Pasal.....");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    public void addPasal(){

        String pasalName=pasalNo.getText().toString().trim();
        String phoneNo=phone.getText().toString().trim();
        String emailAdd=email.getText().toString().trim();
        String priceTag=price.getText().toString().trim();
        String desc=description.getText().toString().trim();
        Integer ca_id=1;
        Integer status_id=1;

        Call<Model_AddPasal> call=apiInterface.addPasal(pasalName,categoryid,status_id,phoneNo,emailAdd,priceTag,desc);
        call.enqueue(new Callback<Model_AddPasal>() {
            @Override
            public void onResponse(Call<Model_AddPasal> call, Response<Model_AddPasal> response) {
                if (response.body().getResponse().equals("ok")){
                    Toast.makeText(AddPasal.this, "Pasal Information Added Successfully", Toast.LENGTH_SHORT).show();
                }
                else if (response.body().getResponse().equals("exist")){
                    Toast.makeText(AddPasal.this, "Pasal Number already exist...", Toast.LENGTH_SHORT).show();
                }
                else if (response.body().getResponse().equals("error")){
                    Toast.makeText(AddPasal.this, "Something went wrong ...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Model_AddPasal> call, Throwable t) {

            }
        });
        pasalNo.setText("");
        phone.setText("");
        email.setText("");
        price.setText("");
        description.setText("");


    }


    @Override
    public void onSuccessCategory(List<ListofCategory> listofCategory) {
        c_id.setAdapter(new ArrayAdapter<>(this, R.
                layout.spinner_layout, R.id.textView ,listofCategory));

    }

    @Override
    public void onSuccessStatus(List<ListofStatus> listofStatus) {

    }

    @Override
    public void onError(String e) {

    }
}
