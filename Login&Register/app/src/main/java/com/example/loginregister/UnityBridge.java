package com.example.loginregister;

import android.content.Intent;
import android.util.Log;

import com.example.loginregister.models.Map;

import android.os.Handler;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnityBridge {
    static Map resMap;
    private static APIInterface apiIface;

    public static void getMap(String level) throws IOException {

        apiIface = APIClient.getClient().create(APIInterface.class);
        resMap = new Map(level);
        Call<Map> call = apiIface.getMap(resMap);
        Response res = call.execute();
        Map p = (Map)res.body();
        resMap.setMap(p.getMap());
        /*
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                String map = response.body().getMap();
                Log.i("grup3", map);
                resMap.setMap(map);
            }
            @Override
            public void onFailure(Call<Map> call, Throwable throwable) {
            }
        });
        */
    }

    public static String getMapString(String level) throws IOException {
        getMap(level);
        return resMap.getMap();
    }
}
