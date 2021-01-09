package com.example.loginregister.ui.shop;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.ShopItem;
import com.example.loginregister.Usuario;
import com.example.loginregister.utils.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ShopFragment extends Fragment{

    private ShopViewModel shopViewModel;
    APIInterface apiIface;
    List<ShopItem> productList = new LinkedList<>();
    MyRecyclerViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        apiIface = APIClient.getClient().create(APIInterface.class);
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        final TextView textView = root.findViewById(R.id.text_shop);
        Call<List<ShopItem>> call = apiIface.getShopItems();
        call.enqueue(new Callback<List<ShopItem>>() {
            @Override
            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    productList = response.body();
                    RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.productsTable);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new MyRecyclerViewAdapter(getContext(), productList);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getContext(), "Error when connecting to the shop.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<ShopItem>> call, Throwable throwable) {
                call.cancel();
            }
        });

        shopViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }

}