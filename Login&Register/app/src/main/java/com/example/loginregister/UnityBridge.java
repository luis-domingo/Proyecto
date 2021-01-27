package com.example.loginregister;

import android.util.Log;

import com.example.loginregister.models.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnityBridge {
    Map resMap;

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
                Log.i("grup3", "onFailure: getMap");
            }
        });
        Log.d("Mapa returned", resMap.getMap());

        return resMap.getMap();
    }

}
