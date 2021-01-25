package com.example.loginregister.ui.profile;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.models.Img_Pojo;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    SharedPreferences sharedPreferences;
    ImageView image;
    int REQUEST_IMAGE_CAPTURE;
    int PHOTO_SELECTED;
    TextView textUser;
    TextView textID;
    Bitmap photo;
    Spinner sp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        sharedPreferences = this.getActivity().getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        image = (ImageView)root.findViewById(R.id.imageView3);
        textUser = (TextView)root.findViewById(R.id.txtUsername);
        textID = (TextView)root.findViewById(R.id.txtID);
        textUser.setText(sharedPreferences.getAll().get("Username").toString());
        textID.setText(sharedPreferences.getAll().get("ID").toString());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                REQUEST_IMAGE_CAPTURE = 1;
                PHOTO_SELECTED = 1;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                Intent chooser = Intent.createChooser(galleryIntent, "Upload Picture");
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cameraIntent});
                startActivityForResult(Intent.createChooser(chooser, "Upload Picture"), REQUEST_IMAGE_CAPTURE);
            }

        };
        image.setOnClickListener(onClickListener);
        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK)
        {
            if(data.getExtras() == null) {
                Picasso.get().load(data.getData()).into(image);
                try {
                    photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                photo = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(photo);
            }
            uploadImage();
        }
    }
    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
    private void uploadImage(){
        String image = convertToString();
        String imageName = sharedPreferences.getAll().get("ID").toString();
        APIInterface apiIface = APIClient.getClient().create(APIInterface.class);
        Call<Img_Pojo> call = apiIface.setImage(imageName, image);
        call.enqueue(new Callback<Img_Pojo>(){
            @Override
            public void onResponse(Call<Img_Pojo> call, Response<Img_Pojo> response) {
                Img_Pojo img_pojo = response.body();
                Log.i("grup3", "Done!" + img_pojo.getResponse());
            }
            @Override
            public void onFailure(Call<Img_Pojo> call, Throwable throwable) {
                Log.i("grup3", "Server Response : " + throwable.toString());
            }
        });
    }
}