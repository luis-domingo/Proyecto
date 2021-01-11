package com.example.loginregister.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;
import com.example.loginregister.ShopItem;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    List<ShopItem> productList = new LinkedList<>();
    Context context;


    public MyRecyclerViewAdapter(Context context, List<ShopItem> productList) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.txtViewTitle.setText(productList.get(position).getName());
        Log.i("grup3", String.valueOf(productList.get(position).getPrice()));
        viewHolder.txtViewPrice.setText(String.valueOf(productList.get(position).getPrice()));
        //Bitmap bmp = BitmapFactory.decodeFile("@drawable://item" + productList.get(position).getIdString());
        try {
            URL url = new URL("http://147.83.7.205:8080/" + productList.get(position).getIdString() + ".jpg");
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            viewHolder.imgViewIcon.setImageBitmap(image);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;
        public TextView txtViewPrice;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.shopItemName);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.shopItemImage);
            txtViewPrice = (TextView) itemLayoutView.findViewById(R.id.shopItemPrice);
        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }
}