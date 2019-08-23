package com.rizieq.androiddrinkshopserver.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rizieq.androiddrinkshopserver.R;
import com.rizieq.androiddrinkshopserver.adapter.viewHolder.MenuViewHolder;
import com.rizieq.androiddrinkshopserver.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {


    Context context;
    List<Category> categoryList;

    public MenuAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

        Picasso.with(context)
                .load(categoryList.get(i).Link)
                .into(menuViewHolder.img_product);

        menuViewHolder.txt_product.setText(categoryList.get(i).Name);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
