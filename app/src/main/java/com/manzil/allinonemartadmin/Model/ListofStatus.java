package com.manzil.allinonemartadmin.Model;

import com.google.gson.annotations.SerializedName;

public class ListofStatus {
    @SerializedName("status")
    private String status;

    //constructior
    public ListofStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
