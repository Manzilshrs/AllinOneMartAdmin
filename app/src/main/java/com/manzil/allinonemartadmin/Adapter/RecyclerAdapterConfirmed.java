package com.manzil.allinonemartadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manzil.allinonemartadmin.Activities.ConfirmedUserDetail;
import com.manzil.allinonemartadmin.Activities.UpdatePasal;
import com.manzil.allinonemartadmin.Activities.UserDetail;
import com.manzil.allinonemartadmin.Model.ListofConfirmedUser;
import com.manzil.allinonemartadmin.Model.ListofPasal;
import com.manzil.allinonemartadmin.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterConfirmed extends RecyclerView.Adapter<RecyclerAdapterConfirmed.MyViewHolder> {

    private List<ListofConfirmedUser> listofConfirmedUsers;
    private Context context;

    public RecyclerAdapterConfirmed(List<ListofConfirmedUser> listofConfirmedUsers, Context context) {
        this.listofConfirmedUsers = listofConfirmedUsers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmed_raw_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(listofConfirmedUsers.get(position).getPasalName());
        final String Name=listofConfirmedUsers.get(position).getPasalName();
        final String Username=listofConfirmedUsers.get(position).getUsername();
        final String Phone=listofConfirmedUsers.get(position).getPhone();
        final String Address=listofConfirmedUsers.get(position).getAddress();
        final String Purpose=listofConfirmedUsers.get(position).getPurpose();
        final Integer Confirm=listofConfirmedUsers.get(position).getConfirm();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ConfirmedUserDetail.class);
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
        return listofConfirmedUsers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.pasalName);
        }
    }
    public void setFilter(ArrayList<ListofConfirmedUser> newList)
    {
        listofConfirmedUsers=new ArrayList<>();
        listofConfirmedUsers.addAll(newList);
        notifyDataSetChanged();
    }
}
