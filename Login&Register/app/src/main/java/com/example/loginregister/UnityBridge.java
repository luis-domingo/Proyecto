package com.example.loginregister;

import android.util.Log;

import com.example.loginregister.models.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnityBridge {
    Map resMap;
    private static Retrofit retrofit;
    private static GameManagerService myUnityAPI;

    public static String getMap(String level){
        Map resMap = new Map();
        APIInterface apiIface = APIClient.getClient().create(APIInterface.class);
        Call<Map> call = apiIface.getMap(new Map(level));
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                 resMap.setMap(response.body().getMap());
            }
            @Override
            public void onFailure(Call<Map> call, Throwable throwable) {
            }
        });
        return resMap.getMap();
    }

    private static void startRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Attaching Interceptor to a client
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://eetacdsa2.upc.es:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


}
