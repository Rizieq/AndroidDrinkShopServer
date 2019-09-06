package com.rizieq.androiddrinkshopserver.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizieq.androiddrinkshopserver.DrinkListActivity;
import com.rizieq.androiddrinkshopserver.Interface.IItemClickListener;
import com.rizieq.androiddrinkshopserver.R;
import com.rizieq.androiddrinkshopserver.UpdateCategoryActivity;
import com.rizieq.androiddrinkshopserver.adapter.viewHolder.MenuViewHolder;
import com.rizieq.androiddrinkshopserver.model.Category;
import com.rizieq.androiddrinkshopserver.utils.Common;
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
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, final int i) {

        Picasso.with(context)
                .load(categoryList.get(i).Link)
                .into(menuViewHolder.img_product);

        menuViewHolder.txt_product.setText(categoryList.get(i).Name);

        // Implement item Click
        menuViewHolder.setItemClickListener(new IItemClickListener() {

            @Override
            public void onClick(View view, boolean isLongClick) {

                if (isLongClick)
                {
                    // Assign this category to variable global
                    Common.currentCategory = categoryList.get(i);
                    // Start Activity
                    context.startActivity(new Intent(context, UpdateCategoryActivity.class));

                }
                else
                {
                    // Assign this category to variable global
                    Common.currentCategory = categoryList.get(i);
                    // Start Activity
                    context.startActivity(new Intent(context, DrinkListActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
