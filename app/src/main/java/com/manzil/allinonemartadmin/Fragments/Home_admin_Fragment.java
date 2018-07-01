package com.manzil.allinonemartadmin.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.manzil.allinonemartadmin.Activities.Category_view;
import com.manzil.allinonemartadmin.Activities.ConfirmedUser;
import com.manzil.allinonemartadmin.Activities.Upload_advertise_image;
import com.manzil.allinonemartadmin.Activities.Upload_main_image;
import com.manzil.allinonemartadmin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_admin_Fragment extends Fragment {
    CardView categoryView, UserRequest, confirmeduser, status, mainimg, advertise;


    public Home_admin_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_admin_, container, false);

        categoryView = (CardView) view.findViewById(R.id.CategoryView);
        UserRequest = view.findViewById(R.id.UserRequest);
        confirmeduser = view.findViewById(R.id.view);
        status = view.findViewById(R.id.status);
        mainimg = view.findViewById(R.id.mainimg);
        advertise = view.findViewById(R.id.advertise);

        categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Category_view.class);
                startActivity(intent);
            }
        });

        UserRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), com.manzil.allinonemartadmin.Activities.UserRequest.class);
                startActivity(intent);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new User_Request_Fragment()).commit();
            }
        });

        confirmeduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConfirmedUser.class);
                startActivity(intent);
                // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new All_Users_Fragement()).commit();
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), com.manzil.allinonemartadmin.Activities.PasalStatus.class);
                startActivity(intent);
            }
        });

        mainimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Upload_main_image.class);
                        startActivity(intent);

            }
        });

        advertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Upload_advertise_image.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
