package com.rizieq.androiddrinkshopserver.utils;

import com.rizieq.androiddrinkshopserver.model.Category;
import com.rizieq.androiddrinkshopserver.model.Drink;
import com.rizieq.androiddrinkshopserver.model.Order;
import com.rizieq.androiddrinkshopserver.retrofit.FCMRetrofitClient;
import com.rizieq.androiddrinkshopserver.retrofit.IDrinkshopAPI;
import com.rizieq.androiddrinkshopserver.retrofit.IFCMServices;
import com.rizieq.androiddrinkshopserver.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class Common {

    public static Category currentCategory;
    public static Drink currentDrink;
    public static Order currentOrder;

    public static List<Category> menuList = new ArrayList<>();


    public static final String BASE_URL = "http://192.168.1.9/drinkshop/";
    public static final String FCM_URL = "https://fcm.googleapis.com/";

    public static IDrinkshopAPI getAPI() {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkshopAPI.class);
    }

    public static IFCMServices getFCMAPI() {
        return FCMRetrofitClient.getClient(FCM_URL).create(IFCMServices.class);
    }


    public static String convertCodeToStatus(int orderStatus) {
        switch (orderStatus) {
            case 0:
                return "Placed";
            case 1:
                return "Processing";
            case 2:
                return "Shipping";
            case 3:
                return "Shipped";
            case -1:
                return "Cancelled";
            default:
                return "Order Error";

        }
    }
}
