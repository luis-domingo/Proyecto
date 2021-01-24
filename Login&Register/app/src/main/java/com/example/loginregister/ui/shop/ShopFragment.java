package com.example.loginregister.ui.shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.example.loginregister.models.UserItem;
import com.example.loginregister.utils.MyRecyclerViewShopAdapter;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ShopFragment extends Fragment{

    ShopViewModel shopViewModel;
    APIInterface apiIface;
    ProgressBar prg;
    List<ShopItem> productList = new LinkedList<>();
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
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
                    adapter.setOnItemClickListener(new MyRecyclerViewShopAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Log.d("grup3", "onItemClickPosition: " + position);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Confirm payment");
                            builder.setMessage("Are you sure you want to buy " + productList.get(position).getName() + " that costs " + productList.get(position).getPrice()+ "?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getContext(), "You bought " + productList.get(position).getName() + " paying " + productList.get(position).getPrice(), Toast.LENGTH_SHORT).show();
                                    UserItem bought = new UserItem(productList.get(position).getName(), 1);
                                    Call<String> call = apiIface.buyItem(bought, sharedPreferences.getAll().get("ID").toString());
                                    call.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Toast.makeText(getContext(), productList.get(position).getName() + " was added to your inventory!", Toast.LENGTH_SHORT).show();
                                        }
                                        @Override
                                        public void onFailure(Call<String> call, Throwable throwable) {
                                            Toast.makeText(getContext(), "Error when adding " + productList.get(position).getName() + " to your inventory!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    dialog.dismiss();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
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