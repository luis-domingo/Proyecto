package com.example.loginregister.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.models.ForumPublication;
import com.example.loginregister.models.Stats;

import java.util.LinkedList;
import java.util.List;

public class MyRecyclerViewForumPublicationsAdapter extends RecyclerView.Adapter<MyRecyclerViewForumPublicationsAdapter.ViewHolder> {
    List<ForumPublication> forumPublications = new LinkedList<>();
    Context context;


    public MyRecyclerViewForumPublicationsAdapter(Context context, List<ForumPublication> publicationList) {
        this.forumPublications = publicationList;
        this.context = context;
    }

    @Override
    public MyRecyclerViewForumPublicationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.publicationsforumrecyclerview_row, null);

        MyRecyclerViewForumPublicationsAdapter.ViewHolder viewHolder = new MyRecyclerViewForumPublicationsAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewForumPublicationsAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(forumPublications.get(position).getName());
        viewHolder.date.setText(forumPublications.get(position).getDate());
        viewHolder.content.setText(forumPublications.get(position).getContent());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView date;
        public TextView content;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.publicationName);
            date = (TextView) itemLayoutView.findViewById(R.id.publicationDate);
            content = (TextView) itemLayoutView.findViewById(R.id.publicationContent);
        }
    }

    @Override
    public int getItemCount() {
        return forumPublications.size();
    }
}
