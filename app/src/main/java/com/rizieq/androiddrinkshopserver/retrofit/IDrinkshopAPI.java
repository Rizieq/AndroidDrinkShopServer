package com.rizieq.androiddrinkshopserver.retrofit;

import com.rizieq.androiddrinkshopserver.model.Category;
import com.rizieq.androiddrinkshopserver.model.Drink;
import com.rizieq.androiddrinkshopserver.model.Order;

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


    /*
     * CATEGORY MANAGEMENT
     */

    @GET("getMenu.php")
    Observable<List<Category>> getMenu();

    @FormUrlEncoded
    @POST("server/category/add_category.php")
    Observable<String> addNewCategory(@Field("name") String name,
                                      @Field("imgPath") String imgPath);

    @Multipart
    @POST("server/category/upload_category_img.php")
    Call<String> uploadCategoryFile(@Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("server/category/update_category.php")
    Observable<String> updateCategory(@Field("id") String id,
                                      @Field("name") String name,
                                      @Field("imgPath") String imgPath);

    @FormUrlEncoded
    @POST("server/category/delete_category.php")
    Observable<String> deleteCategory(@Field("id") String id);

    /*
     * DRINK MANAGEMENT
     */

    @FormUrlEncoded
    @POST("getdrink.php")
    Observable<List<Drink>> getDrink(@Field("menuid") String menuID);

    @FormUrlEncoded
    @POST("server/product/add_product.php")
    Observable<String> addNewProduct(@Field("name") String name,
                                     @Field("imgPath") String imgPath,
                                     @Field("price") String price,
                                     @Field("menuId") String menuId);

    @Multipart
    @POST("server/product/upload_product_img.php")
    Call<String> uploadProductFile(@Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("server/product/update_product.php")
    Observable<String> updateProduct(@Field("id") String id,
                                     @Field("name") String name,
                                     @Field("imgPath") String imgPath,
                                     @Field("price") String price,
                                     @Field("menuId") String menuId);

    @FormUrlEncoded
    @POST("server/product/delete_product.php")
    Observable<String> deleteProduct(@Field("id") String id);


    /*
     * ORDER MANAGEMENT
     */

    @FormUrlEncoded
    @POST("server/order/getorder.php")
    Observable<List<Order>> getAllOrder(@Field("status") String status);
}
