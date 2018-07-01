package com.manzil.allinonemartadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.manzil.allinonemartadmin.Activities.Category_view;
import com.manzil.allinonemartadmin.Activities.MainActivity;
import com.manzil.allinonemartadmin.Activities.UpdatePasal;
import com.manzil.allinonemartadmin.Activities.UserDetail;
import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.ListofRequest;
import com.manzil.allinonemartadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterRequest extends RecyclerView.Adapter<RecyclerAdapterRequest.MyViewHolder> {

    private List<ListofRequest> listofRequests;
    private ApiInterface apiInterface;
    private Context context;


    public RecyclerAdapterRequest(List<ListofRequest> listofRequests, Context context) {
        this.listofRequests = listofRequests;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.request_layout,parent,false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.name.setText(listofRequests.get(position).getPasalName());
        final String Name=listofRequests.get(position).getPasalName();
        final String Username=listofRequests.get(position).getUsername();
        final String Phone=listofRequests.get(position).getPhone();
        final String Address=listofRequests.get(position).getAddress();
        final String Purpose=listofRequests.get(position).getPurpose();
        final Integer Confirm=listofRequests.get(position).getConfirm();



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UserDetail.class);
                intent.putExtra("as",Name);
                intent.putExtra("username",Username);
                intent.putExtra("phone",Phone);
                intent.putExtra("address",Address);
                intent.putExtra("purpose",Purpose);
                intent.putExtra("confirm",Confirm);
                context.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return listofRequests.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.pasalName);
        }
    }
    public void setFilter(ArrayList<ListofRequest> newList)
    {
        listofRequests=new ArrayList<>();
        listofRequests.addAll(newList );
        notifyDataSetChanged();
    }

}



