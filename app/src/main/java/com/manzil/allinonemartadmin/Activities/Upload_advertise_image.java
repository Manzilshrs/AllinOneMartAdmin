package com.manzil.allinonemartadmin.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.AdvertiseImageClass;
import com.manzil.allinonemartadmin.Model.ForntImageClass;
import com.manzil.allinonemartadmin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;

public class Upload_advertise_image extends AppCompatActivity implements View.OnClickListener{
    private Button chooseimg, uploadimg;
    private ImageView imageView;


    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;
    String P_name;

    Context context;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_advertise_image);

        chooseimg = (Button) findViewById(R.id.chooseimg);
        uploadimg = (Button) findViewById(R.id.upload);
        imageView = findViewById(R.id.imageView);

        chooseimg.setOnClickListener(this);
        uploadimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chooseimg:
                selectImage();
                break;

            case R.id.upload:
                uploadImage();
                break;

        }
    }
        private void selectImage ()
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMG_REQUEST);
        }

    private void uploadImage() {
        String Image = imageToString();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<AdvertiseImageClass> call = apiInterface.uploadAdvertiseImage(Image);
        call.enqueue(new Callback<AdvertiseImageClass>() {
            @Override
            public void onResponse(retrofit2.Call<AdvertiseImageClass> call, Response<AdvertiseImageClass> response) {
                AdvertiseImageClass imageClass = response.body();
                Toast.makeText(Upload_advertise_image.this, "Server Response:" + imageClass.getResponse(), Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.GONE);
                chooseimg.setEnabled(true);
                uploadimg.setEnabled(false);
            }

            @Override
            public void onFailure(retrofit2.Call<AdvertiseImageClass> call, Throwable t) {
                Toast.makeText(Upload_advertise_image.this, "onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                chooseimg.setEnabled(false);
                uploadimg.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
