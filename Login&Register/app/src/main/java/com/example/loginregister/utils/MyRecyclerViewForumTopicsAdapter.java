package com.example.loginregister.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.models.ForumTopic;

import java.util.LinkedList;
import java.util.List;


public class MyRecyclerViewForumTopicsAdapter extends RecyclerView.Adapter<MyRecyclerViewForumTopicsAdapter.ViewHolder>{
    List<ForumTopic> topicList = new LinkedList<>();
    Context context;
    private static ClickListener clickListener;


    public MyRecyclerViewForumTopicsAdapter(Context context, List<ForumTopic> topicList) {
        this.topicList = topicList;
        this.context = context;
    }

    @Override
    public MyRecyclerViewForumTopicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View forumLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topicsforumrecyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(forumLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.txtViewTitle.setText(topicList.get(position).getTitle());
        viewHolder.txtViewNumber.setText(String.valueOf(topicList.get(position).getNumPublications()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtViewTitle;
        public TextView txtViewNumber;

        public ViewHolder(View forumLayoutView) {
            super(forumLayoutView);
            forumLayoutView.setOnClickListener(this);
            txtViewTitle = (TextView) forumLayoutView.findViewById(R.id.forumTxtTitle);
            txtViewNumber = (TextView) forumLayoutView.findViewById(R.id.forumTxtNumber);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

    }

    public void setOnItemClickListener(ClickListener clickListener){
        MyRecyclerViewForumTopicsAdapter.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(int position, View v);
    }




    @Override
    public int getItemCount() {
        return topicList.size();
    }
}
