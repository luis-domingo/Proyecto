package com.example.loginregister;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/dsaApp/usuarios/login")
    Call<Usuario> loginUser(@Body Usuario user);

    @POST("/dsaApp/usuarios/newuser")
    Call<Usuario> createUser(@Body Usuario user);

    @GET("/dsaApp/shop/listObjects")
    Call<List<ShopItem>> getShopItems();

    @GET("/dsaApp/inventory/listObjects")
    Call<List<UserItem>> getUserItems(@Body String id);

}
