package com.rizieq.androiddrinkshopserver.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizieq.androiddrinkshopserver.Interface.IItemClickListener;
import com.rizieq.androiddrinkshopserver.R;
import com.rizieq.androiddrinkshopserver.adapter.viewHolder.DrinkListViewHolder;
import com.rizieq.androiddrinkshopserver.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListViewHolder> {

    Context context;
    List<Drink> drinkList;


    public DrinkListAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.drink_item_layout,viewGroup,false);

        return new DrinkListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkListViewHolder drinkListViewHolder, int i) {

        Picasso.with(context).load(drinkList.get(i).Link).into(drinkListViewHolder.img_product);
        drinkListViewHolder.txt_price.setText(new StringBuilder("$").append(drinkList.get(i).Price).toString());
        drinkListViewHolder.txt_drink_name.setText(drinkList.get(i).Name);

        // Event -anti crash null item click
        drinkListViewHolder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, boolean isLongClick) {
                // Implement late
            }
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
}
