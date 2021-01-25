package com.example.loginregister;

import com.example.loginregister.models.Img_Pojo;
import com.example.loginregister.models.ShopItem;
import com.example.loginregister.models.UserItem;
import com.example.loginregister.models.Usuario;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Param;
import retrofit2.http.ParamQuerys;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("/dsaApp/usuarios/login")
    Call<Usuario> loginUser(@Body Usuario user);

    @POST("/dsaApp/usuarios/newuser")
    Call<Usuario> createUser(@Body Usuario user);

    @GET("/dsaApp/shop/listObjects")
    Call<List<ShopItem>> getShopItems();

    @POST("/dsaApp/inventory/listObjects")
    Call<List<UserItem>> getUserItems(@Body String id);

    @POST("/dsaApp/inventory/buyItem")
    Call<Void> buyItem (@Body UserItem item);

    @Multipart
    @POST("/dsaApp/usuarios/setImage/{id}")
    Call<Img_Pojo> setImage (@Path("id") String id, @Part String image);
}
