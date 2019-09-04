package com.rizieq.androiddrinkshopserver.utils;

import com.rizieq.androiddrinkshopserver.retrofit.IDrinkshopAPI;
import com.rizieq.androiddrinkshopserver.retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class Common {

    public static final String BASE_URL = "http://192.168.0.111/drinkshop/";
    public static IDrinkshopAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkshopAPI.class);
    }

}
