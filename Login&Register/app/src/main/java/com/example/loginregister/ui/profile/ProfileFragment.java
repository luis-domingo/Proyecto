package com.example.loginregister.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginregister.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView image = (ImageView)root.findViewById(R.id.imageView3);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Ho has fet molt bé!", Toast.LENGTH_SHORT).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                int REQUEST_IMAGE_CAPTURE = 1;
                Intent galleryIntent = new Intent().setAction(Intent.ACTION_GET_CONTENT).setType("image/*");
                Intent chooser = Intent.createChooser(galleryIntent, "Upload Picture");
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cameraIntent});
                startActivityForResult(Intent.createChooser(chooser, "Upload Picture"), REQUEST_IMAGE_CAPTURE);
            }
        };
        image.setOnClickListener(onClickListener);
        return root;
    }

}