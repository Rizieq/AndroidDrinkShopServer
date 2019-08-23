package com.rizieq.androiddrinkshopserver.retrofit;

import com.rizieq.androiddrinkshopserver.model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IDrinkshopAPI {


    @GET("getMenu.php")
    Observable<List<Category>> getMenu();
}
