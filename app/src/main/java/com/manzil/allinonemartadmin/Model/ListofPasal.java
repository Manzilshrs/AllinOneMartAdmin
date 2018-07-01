package com.manzil.allinonemartadmin.Model;

import com.google.gson.annotations.SerializedName;

public class ListofPasal {

    @SerializedName("name")
    private String PasalName;

    @SerializedName("c_id")
    private String C_id;

    @SerializedName("status")
    private String Status;

    @SerializedName("phone")
    private String Phone;

    @SerializedName("email")
    private String Email;

    @SerializedName("price")
    private String Price;

    @SerializedName("description")
    private String Description;

    public String getPasalName()

    {
        return PasalName;
    }

    public String getC_id() {
        return C_id;
    }

    public String getStatus() {
        return Status;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }
}
