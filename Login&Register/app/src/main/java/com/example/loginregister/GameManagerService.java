package com.example.loginregister;

import com.example.loginregister.models.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface GameManagerService {

    @GET("game/getMap")
    Call<Map> getMap (@Body int id);


}
