package com.manzil.allinonemartadmin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manzil.allinonemartadmin.Activities.UpdatePasal;
import com.manzil.allinonemartadmin.Model.ListofPasal;
import com.manzil.allinonemartadmin.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterUpdate extends RecyclerView.Adapter<RecyclerAdapterUpdate.MyViewHolder> {

    private List<ListofPasal> listofPasals;
    private Context context;

    public RecyclerAdapterUpdate(List<ListofPasal> listofPasals, Context context) {
        this.listofPasals = listofPasals;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.update_raw_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.name.setText(listofPasals.get(position).getPasalName());
     final String Name=listofPasals.get(position).getPasalName();
     final String C_id=listofPasals.get(position).getC_id();
     final String Status=listofPasals.get(position).getStatus();
     final String Phone=listofPasals.get(position).getPhone();
     final String Email=listofPasals.get(position).getEmail();
     final String Price=listofPasals.get(position).getPrice();
     final String Description=listofPasals.get(position).getDescription();

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent=new Intent(context, UpdatePasal.class);
             intent.putExtra("as",Name);
             intent.putExtra("c_id",C_id);
             intent.putExtra("status",Status);
             intent.putExtra("phone",Phone);
             intent.putExtra("email",Email);
             intent.putExtra("price",Price);
             intent.putExtra("description",Description);
            context.startActivity(intent);

         }
     });
    }

    @Override
    public int getItemCount() {
        return listofPasals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.pasalName);
        }
    }
    public void setFilter(ArrayList<ListofPasal> newList)
    {
        listofPasals=new ArrayList<>();
        listofPasals.addAll(newList );
        notifyDataSetChanged();
    }
}
