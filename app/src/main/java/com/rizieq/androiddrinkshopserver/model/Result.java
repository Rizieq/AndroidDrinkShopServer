package com.rizieq.androiddrinkshopserver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("message_id")
    @Expose
    public String message_id;
}