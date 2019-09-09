package com.rizieq.androiddrinkshopserver.utils;

import com.rizieq.androiddrinkshopserver.model.Category;
import com.rizieq.androiddrinkshopserver.model.Drink;
import com.rizieq.androiddrinkshopserver.retrofit.IDrinkshopAPI;
import com.rizieq.androiddrinkshopserver.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class Common {

    public static Category currentCategory;
    public static Drink currentDrink;

    public static List<Category> menuList = new ArrayList<>();


    public static final String BASE_URL = "http://192.168.0.111/drinkshop/";
    public static IDrinkshopAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkshopAPI.class);
    }

}
