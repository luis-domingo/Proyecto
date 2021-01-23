package com.example.loginregister.ui.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.models.ShopItem;
import com.example.loginregister.utils.MyRecyclerViewShopAdapter;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopFragment extends Fragment{

    ShopViewModel shopViewModel;
    APIInterface apiIface;
    ProgressBar prg;
    List<ShopItem> productList = new LinkedList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        apiIface = APIClient.getClient().create(APIInterface.class);
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shop, container, false);
        prg = (ProgressBar)root.findViewById(R.id.progressBar);
        prg.setVisibility(View.VISIBLE);
        Call<List<ShopItem>> call = apiIface.getShopItems();
        call.enqueue(new Callback<List<ShopItem>>() {
            @Override
            public void onResponse(Call<List<ShopItem>> call, Response<List<ShopItem>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    productList = response.body();
                    RecyclerView myRecyclerView = (RecyclerView)root.findViewById(R.id.productsTable);
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    MyRecyclerViewShopAdapter adapter;
                    adapter = new MyRecyclerViewShopAdapter(getContext(), productList);
                    myRecyclerView.setAdapter(adapter);
                    prg.setVisibility(View.GONE);
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


        return root;
    }

}