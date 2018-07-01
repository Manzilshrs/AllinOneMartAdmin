package com.manzil.allinonemartadmin.Model;

import com.google.gson.annotations.SerializedName;

public class ListofCategory {
    @SerializedName("category")
    private String category;

    //constructior
    public ListofCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
