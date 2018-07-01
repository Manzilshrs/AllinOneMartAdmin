package com.manzil.allinonemartadmin.Activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;


import com.manzil.allinonemartadmin.Adapter.RecyclerAdapterRequest;
import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.ListofPasal;
import com.manzil.allinonemartadmin.Model.ListofRequest;
import com.manzil.allinonemartadmin.R;

import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRequest extends AppCompatActivity  implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ListofRequest> requestList;
    private RecyclerAdapterRequest adapter;
    private ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewRequest);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (getIntent() != null) {
            fetchPasalName();
        }
    }

    public void fetchPasalName() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ListofRequest>> call=apiInterface.getuserreq();
        call.enqueue(new Callback<List<ListofRequest>>() {
            @Override
            public void onResponse(Call<List<ListofRequest>> call, Response<List<ListofRequest>> response) {
                requestList = response.body();
                adapter = new RecyclerAdapterRequest(requestList, UserRequest.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListofRequest>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<ListofRequest> newList = new ArrayList<>();
        for (ListofRequest listofRequest: requestList)
        {
            String name=listofRequest.getPasalName().toLowerCase();
            if (name.contains(newText))
                newList.add(listofRequest);
        }
        adapter.setFilter(newList);
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}


