package com.manzil.allinonemartadmin.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.ImageClass;
import com.manzil.allinonemartadmin.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Callback;
import retrofit2.Response;

public class Upload_image extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_upload_image);

        chooseimg = (Button) findViewById(R.id.chooseimg);
        uploadimg = (Button) findViewById(R.id.upload);
        imageView = findViewById(R.id.imageView);

        chooseimg.setOnClickListener(this);
        uploadimg.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            P_name = (String) b.get("as");
            // pstatus = (Integer) bd.get("status");
        }
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

    private void uploadImage() {
        String Image = imageToString();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<ImageClass> call = apiInterface.uploadImage(P_name, Image);
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(retrofit2.Call<ImageClass> call, Response<ImageClass> response) {
                ImageClass imageClass = response.body();
                Toast.makeText(Upload_image.this, "Server Response:" + imageClass.getResponse(), Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.GONE);
                chooseimg.setEnabled(true);
                uploadimg.setEnabled(false);
            }

            @Override
            public void onFailure(retrofit2.Call<ImageClass> call, Throwable t) {
                Toast.makeText(Upload_image.this, "onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
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


}
