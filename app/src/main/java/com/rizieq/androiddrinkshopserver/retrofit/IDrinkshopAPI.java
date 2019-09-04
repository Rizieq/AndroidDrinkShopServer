package com.rizieq.androiddrinkshopserver.retrofit;

import com.rizieq.androiddrinkshopserver.model.Category;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IDrinkshopAPI {


    @GET("getMenu.php")
    Observable<List<Category>> getMenu();

    @FormUrlEncoded
    @POST("server/category/add_category.php")
    Observable<String> addNewCategory(@Field("name") String name,
                                      @Field("imgPath") String imgPath);

    @Multipart
    @POST("server/category/upload_category_img.php")
    Call<String> uploadCategoryFile(@Part MultipartBody.Part file);
}
