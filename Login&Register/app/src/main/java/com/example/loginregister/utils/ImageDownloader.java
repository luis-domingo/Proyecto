package com.example.loginregister.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ImageDownloader {
    APIInterface apiIface;

    public Bitmap downloadImage(String ID){

        apiIface = APIClient.getClient().create(APIInterface.class);
        Call<String> call = apiIface.getImage(ID);
        final Bitmap[] bitmap = {null};
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200){
                    Log.i("grup3", "Ho has fet molt bé");
                    String imatge = response.body().replace("\n", "");
                    byte[] bytes = Base64.decode(imatge, Base64.DEFAULT);
                    bitmap[0] = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                }
                else{
                    Log.i("grup3", "No ho has fet tan bé (else)");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.i("grup3", "No ho has fet tan bé (failure)");
            }
        });
        return bitmap[0];
    }
}
