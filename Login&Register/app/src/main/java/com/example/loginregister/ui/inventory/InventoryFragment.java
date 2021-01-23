package com.example.loginregister.ui.inventory;

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
import com.example.loginregister.UserItem;
import com.example.loginregister.utils.MyRecyclerViewInventoryAdapter;
import com.example.loginregister.utils.MyRecyclerViewShopAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class InventoryFragment extends Fragment {

    SharedPreferences sharedPreferences;
    InventoryViewModel inventoryViewModel;
    APIInterface apiIface;
    ProgressBar prg;
    List<UserItem> itemList = new LinkedList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
        apiIface = APIClient.getClient().create(APIInterface.class);
        inventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);
        prg = (ProgressBar)root.findViewById(R.id.progressBar);
        prg.setVisibility(View.VISIBLE);
        Call<List<UserItem>> call = apiIface.getUserItems(sharedPreferences.getAll().get("ID").toString());
        call.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    itemList = response.body();
                    RecyclerView myRecyclerView = (RecyclerView)root.findViewById(R.id.itemsTable);
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    MyRecyclerViewInventoryAdapter adapter;
                    adapter = new MyRecyclerViewInventoryAdapter(getContext(), itemList);
                    myRecyclerView.setAdapter(adapter);
                    prg.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(getContext(), "Error when connecting to the shop.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable throwable) {
                call.cancel();
            }
        });


        return root;
    }

}