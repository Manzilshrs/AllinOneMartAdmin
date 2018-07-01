package com.manzil.allinonemartadmin.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.manzil.allinonemartadmin.Activities.Category_view;
import com.manzil.allinonemartadmin.Activities.MainActivity;
import com.manzil.allinonemartadmin.Activities.UpdatePasal;
import com.manzil.allinonemartadmin.Activities.Upload_image;
import com.manzil.allinonemartadmin.DataManager.ApiClient;
import com.manzil.allinonemartadmin.DataManager.ApiInterface;
import com.manzil.allinonemartadmin.Model.ListofPasal;
import com.manzil.allinonemartadmin.Model.Model_DeletePasal;
import com.manzil.allinonemartadmin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterDelete extends RecyclerView.Adapter<RecyclerAdapterDelete.MyViewHolder> {

    private List<ListofPasal> listofPasals;
    private ApiInterface apiInterface;
    private Context context;


    public RecyclerAdapterDelete(List<ListofPasal> listofPasals, Context context) {
        this.listofPasals = listofPasals;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.delete_raw_layout,parent,false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.name.setText(listofPasals.get(position).getPasalName());
       final String Name=listofPasals.get(position).getPasalName();


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //deletePasal(Name);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you Sure, Delete Pasal?").setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context, ""+Name, Toast.LENGTH_SHORT).show();
                        Call<Model_DeletePasal> call =apiInterface.deletePasal(Name);
                        call.enqueue(new Callback<Model_DeletePasal>() {
                            @Override
                            public void onResponse(Call<Model_DeletePasal> call, Response<Model_DeletePasal> response) {
                                if (response.body().getResponse().equals("ok"))
                                {
                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(context, MainActivity.class);
                                    context.startActivity(intent);



                                }
                                else if (response.body().getResponse().equals("failed"))
                                {
                                    Toast.makeText(context, "Delete Unsuccessful...Try again", Toast.LENGTH_SHORT).show();
                                }
                                else if (response.body().getResponse().equals("missing"))
                                {
                                    Toast.makeText(context, "Required field are missing...", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Model_DeletePasal> call, Throwable t) {
                                Toast.makeText(context, "on failure", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();
                alert.getWindow().setBackgroundDrawableResource(android.R.color.holo_blue_dark);


            }
        });
    }

//    public void deletePasal(String name){
//
//
//    }

    @Override
    public int getItemCount() {
        return listofPasals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CardView delete;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.pasalName);
            delete=(CardView) itemView.findViewById(R.id.delete);
        }
    }
    public void setFilter(ArrayList<ListofPasal> newList)
    {
        listofPasals=new ArrayList<>();
        listofPasals.addAll(newList );
        notifyDataSetChanged();
    }
}



