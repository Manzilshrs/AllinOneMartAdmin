package com.manzil.allinonemartadmin.Model;

import com.google.gson.annotations.SerializedName;

public class ImageClass {

    @SerializedName("p_no")
    private String pasalNo;

    @SerializedName("image")
    private String Image;

    @SerializedName("response")
    private String Response;

    public String getResponse()
    {
        return Response;
    }
}
