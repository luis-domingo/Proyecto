package com.example.loginregister.ui.stats;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.APIClient;
import com.example.loginregister.APIInterface;
import com.example.loginregister.R;
import com.example.loginregister.models.Stats;
import com.example.loginregister.models.UserItem;
import com.example.loginregister.utils.MyRecyclerViewInventoryAdapter;
import com.example.loginregister.utils.MyRecyclerViewStatsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsFragment extends Fragment {

    private StatsViewModel statsViewModel;
    APIInterface apiIface;
    ProgressBar prg;
    List<Stats> statsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stats, container, false);

        apiIface = APIClient.getClient().create(APIInterface.class);
        prg = (ProgressBar)root.findViewById(R.id.progressBar);
        prg.setVisibility(View.VISIBLE);
        Call<List<Stats>> call = apiIface.getStats();
        call.enqueue(new Callback<List<Stats>>() {
            @Override
            public void onResponse(Call<List<Stats>> call, Response<List<Stats>> response) {
                if (response.code() == 200){
                    Log.d("INFO", "onResponse: " + response.body());
                    statsList = response.body();
                    RecyclerView myRecyclerView = (RecyclerView)root.findViewById(R.id.statsTable);
                    myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    MyRecyclerViewStatsAdapter adapter;
                    adapter = new MyRecyclerViewStatsAdapter(getContext(), statsList);
                    myRecyclerView.setAdapter(adapter);
                    prg.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(getContext(), "Error when connecting to the database.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Stats>> call, Throwable throwable) {
                call.cancel();
            }
        });


        return root;
    }
}