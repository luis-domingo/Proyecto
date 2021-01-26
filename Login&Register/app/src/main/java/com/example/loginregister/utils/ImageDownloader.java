package com.example.loginregister.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.models.UserImg;

import org.w3c.dom.Text;

import javax.ws.rs.core.MediaType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ImageDownloader {
    APIInterface apiIface;
    Bitmap bitmap;

    public void downloadAndSetImage(String ID, ImageView imageView){

        apiIface = APIClient.getClient().create(APIInterface.class);
        UserImg auxUserImg = new UserImg(ID);
        Call<UserImg> call = apiIface.getImage(auxUserImg);

        call.enqueue(new Callback<UserImg>() {
            @Override
            public void onResponse(Call<UserImg> call, Response<UserImg> response) {
                if(response.code() == 200){
                    Log.i("grup3", "Ho has fet molt bé");
                    String imatge = response.body().getImage().replace("\n", "");
                    byte[] bytes = Base64.decode(imatge, Base64.DEFAULT);
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                    imageView.setImageBitmap(bitmap);
                }
                else{
                    Log.i("grup3", "No ho has fet tan bé (else)");
                }
            }
            @Override
            public void onFailure(Call<UserImg> call, Throwable throwable) {
                Log.i("grup3", "No ho has fet tan bé (failure)");
            }
        });
    }
}
