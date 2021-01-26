package com.example.loginregister.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.models.Stats;

import java.util.LinkedList;
import java.util.List;

public class MyRecyclerViewStatsAdapter extends RecyclerView.Adapter<MyRecyclerViewStatsAdapter.ViewHolder> {
    List<Stats> stats = new LinkedList<>();
    Context context;


    public MyRecyclerViewStatsAdapter(Context context, List<Stats> statsList) {
        this.stats = statsList;
        this.context = context;
    }

    @Override
    public MyRecyclerViewStatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statsrecyclerview_row, null);

        MyRecyclerViewStatsAdapter.ViewHolder viewHolder = new MyRecyclerViewStatsAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewStatsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.statsName.setText(stats.get(position).getName());
        viewHolder.statsGames.setText(stats.get(position).getGames());
        viewHolder.statsBest.setText(stats.get(position).getBest());
        viewHolder.statsLast.setText(stats.get(position).getLast());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView statsName;
        public TextView statsGames;
        public TextView statsBest;
        public TextView statsLast;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            statsName = (TextView) itemLayoutView.findViewById(R.id.publicationName);
            statsGames = (TextView) itemLayoutView.findViewById(R.id.publicationDate);
            statsBest = (TextView) itemLayoutView.findViewById(R.id.publicationContent);
            statsLast = (TextView) itemLayoutView.findViewById(R.id.statsLast);
        }
    }
    
    @Override
    public int getItemCount() {
        return stats.size();
    }
}
