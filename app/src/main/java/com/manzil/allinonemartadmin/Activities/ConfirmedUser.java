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
import android.support.v7.widget.SearchView;

import com.manzil.allinonemartadmin.Adapter.RecyclerAdapterConfirmed;
import com.manzil.allinonemartadmin.Adapter.RecyclerAdapterRequest;
import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.ListofConfirmedUser;
import com.manzil.allinonemartadmin.Model.ListofRequest;
import com.manzil.allinonemartadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmedUser extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ListofConfirmedUser> confirmedUsers;
    private RecyclerAdapterConfirmed adapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewConfirmed);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (getIntent() != null) {
            fetchPasalName();
        }
    }


    public void fetchPasalName() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ListofConfirmedUser>> call=apiInterface.getconfirmeduser();
        call.enqueue(new Callback<List<ListofConfirmedUser>>() {
            @Override
            public void onResponse(Call<List<ListofConfirmedUser>> call, Response<List<ListofConfirmedUser>> response) {
                confirmedUsers = response.body();
                adapter = new RecyclerAdapterConfirmed(confirmedUsers, ConfirmedUser.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ListofConfirmedUser>> call, Throwable t) {

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
        ArrayList<ListofConfirmedUser> newList = new ArrayList<>();
        for (ListofConfirmedUser listofConfirmedUser: confirmedUsers)
        {
            String name=listofConfirmedUser.getPasalName().toLowerCase();
            if (name.contains(newText))
                newList.add(listofConfirmedUser);
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

