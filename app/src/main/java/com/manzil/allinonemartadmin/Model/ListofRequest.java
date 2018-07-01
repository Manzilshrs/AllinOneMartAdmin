package com.manzil.allinonemartadmin.Model;

import com.google.gson.annotations.SerializedName;

public class ListofRequest {

    @SerializedName("pname")
    private String PasalName;

    @SerializedName("name")
    private String Username;

    @SerializedName("phone")
    private String Phone;

    @SerializedName("address")
    private String Address;

    @SerializedName("purpose")
    private String Purpose;

  @SerializedName("confirm")
    private Integer Confirm;

    public Integer getConfirm() {
        return Confirm;
    }

    public String getPasalName() {
        return PasalName;
    }

    public String getUsername() {
        return Username;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getPurpose() {
        return Purpose;
    }
}
