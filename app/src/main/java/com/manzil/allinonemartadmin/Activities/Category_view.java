package com.manzil.allinonemartadmin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.manzil.allinonemartadmin.R;

public class Category_view extends AppCompatActivity {

    CardView add, update, delete,uploadimg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        add = findViewById(R.id.add);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        uploadimg=findViewById(R.id.uploadimg);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category_view.this, AddPasal.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category_view.this, ListPasalUpdate.class);
                startActivity(intent);
            }
        });

uploadimg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Category_view.this,ListPasalUpload.class);
        startActivity(intent);
    }
});

delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Category_view.this,ListPasalDelete.class);
        startActivity(intent);

    }
});
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(Category_view.this,MainActivity.class);
        startActivity(i);
    }
}
