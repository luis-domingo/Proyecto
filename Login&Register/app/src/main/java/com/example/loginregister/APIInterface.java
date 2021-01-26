package com.example.loginregister;

import com.example.loginregister.models.ForumPublication;
import com.example.loginregister.models.ForumTopic;
import com.example.loginregister.models.ShopItem;
import com.example.loginregister.models.Stats;
import com.example.loginregister.models.UserImg;
import com.example.loginregister.models.UserItem;
import com.example.loginregister.models.Usuario;

import org.w3c.dom.Text;

import java.util.List;
import javax.ws.rs.core.GenericEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


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

    @POST("/dsaApp/usuarios/setImage")
    Call<Void> setImage (@Body UserImg image);

    @GET("/dsaApp/stats/getAllStats")
    Call<List<Stats>> getStats();

    @POST("/dsaApp/usuarios/getImage")
    Call<UserImg> getImage (@Body UserImg auxUserImg);

    @PUT("/dsaApp/forum/addTopic")
    Call<Void> addTopic(@Body ForumTopic topic);

    @PUT("dsaApp/forum/addPublication")
    Call<Void> addPublication(@Body ForumPublication publication);

    @POST("/dsaApp/forum/listTopics")
    Call<List<ForumTopic>> getTopics();

    @POST("/dsaApp/forum/listPublications")
    Call<List<ForumPublication>> getPublications(@Body ForumPublication publication);
}
