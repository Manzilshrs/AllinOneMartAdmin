package com.manzil.allinonemartadmin.Activities;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.manzil.allinonemartadmin.Adapter.RecyclerAdapterUpdate;
import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.ListofPasal;
import com.manzil.allinonemartadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPasalUpdate extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ListofPasal> listPasal;
    private RecyclerAdapterUpdate adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_pasal_upadate);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        if (getIntent() != null) {
            fetchPasalName();
        }
    }

    public void fetchPasalName() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ListofPasal>> call = apiInterface.getpasalName();
        call.enqueue(new Callback<List<ListofPasal>>() {
            @Override
            public void onResponse(Call<List<ListofPasal>> call, Response<List<ListofPasal>> response) {
                listPasal = response.body();
                adapter = new RecyclerAdapterUpdate(listPasal, ListPasalUpdate.this);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ListofPasal>> call, Throwable t) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
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
        ArrayList<ListofPasal> newList = new ArrayList<>();
        for (ListofPasal listofPasal : listPasal) {
            String name = listofPasal.getPasalName().toLowerCase();
            if (name.contains(newText))
                newList.add(listofPasal);
        }
        adapter.setFilter(newList);
        return true;
    }
}
