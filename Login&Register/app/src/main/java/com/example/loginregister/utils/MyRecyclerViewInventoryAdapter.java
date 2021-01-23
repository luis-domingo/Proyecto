package com.example.loginregister.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;

import java.util.LinkedList;
import java.util.List;

import com.example.loginregister.models.UserItem;


public class MyRecyclerViewInventoryAdapter extends RecyclerView.Adapter<MyRecyclerViewInventoryAdapter.ViewHolder> {
    List<UserItem> productList = new LinkedList<>();
    Context context;


    public MyRecyclerViewInventoryAdapter(Context context, List<UserItem> productList) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public MyRecyclerViewInventoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventoryrecyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.txtViewTitle.setText(productList.get(position).getName());
        viewHolder.txtViewNumber.setText(String.valueOf(productList.get(position).getQuantity()));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public TextView txtViewNumber;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.inventoryItemName);
            txtViewNumber = (TextView) itemLayoutView.findViewById(R.id.inventoryItemNumber);
        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}
