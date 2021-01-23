package com.example.loginregister.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.models.ShopItem;

import java.util.LinkedList;
import java.util.List;

import com.squareup.picasso.Picasso;


public class MyRecyclerViewShopAdapter extends RecyclerView.Adapter<MyRecyclerViewShopAdapter.ViewHolder> {
    List<ShopItem> productList = new LinkedList<>();
    Context context;


    public MyRecyclerViewShopAdapter(Context context, List<ShopItem> productList) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public MyRecyclerViewShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shoprecyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.txtViewTitle.setText(productList.get(position).getName());
        viewHolder.txtViewPrice.setText(String.valueOf(productList.get(position).getPrice()));
        Picasso.get().load("http://147.83.7.205:8080/" + productList.get(position).getIdString() + ".jpg").into(viewHolder.imgViewIcon);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;
        public TextView txtViewPrice;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.shopItemName);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.shopItemImage);
            txtViewPrice = (TextView) itemLayoutView.findViewById(R.id.shopItemNumber);
        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}