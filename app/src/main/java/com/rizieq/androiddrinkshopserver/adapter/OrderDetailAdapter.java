package com.rizieq.androiddrinkshopserver.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rizieq.androiddrinkshopserver.R;
import com.rizieq.androiddrinkshopserver.model.Cart;
import com.rizieq.androiddrinkshopserver.utils.Common;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder> {

    Context context;
    List<Cart> itemList;

    public OrderDetailAdapter(Context context) {
        this.context = context;
        this.itemList = new Gson().fromJson(Common.currentOrder.getOrderDetail(), new TypeToken<List<Cart>>(){}.getType());
    }

    @NonNull
    @Override
    public OrderDetailAdapter.OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_detail_layout,viewGroup,false);
        return new OrderDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.OrderDetailViewHolder orderDetailViewHolder, int i) {

        orderDetailViewHolder.txt_drink_amount.setText(""+itemList.get(i).getAmount());
        orderDetailViewHolder.txt_drink_name.setText(new StringBuilder(itemList.get(i).getName()));
        orderDetailViewHolder.txt_size.setText(itemList.get(i).getSize() == 0?"Size M":"Size L");
        orderDetailViewHolder.txt_sugar_ice.setText(new StringBuilder("Sugar :").append(itemList.get(i).getSugar())
        .append(", Ice: ").append(itemList.get(i).getIce()));

        String topping_format = itemList.get(i).getToppingExtras().replaceAll("\\n",",");
        topping_format = topping_format.substring(0,topping_format.length()-1);

        orderDetailViewHolder.txt_topping.setText(topping_format);

        Picasso.with(context).load(itemList.get(i).getLink()).into(orderDetailViewHolder.img_order_item);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView img_order_item;
        TextView txt_drink_name,txt_drink_amount,txt_sugar_ice,txt_size,txt_topping;

        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            img_order_item = itemView.findViewById(R.id.img_order_item);

            txt_drink_amount = itemView.findViewById(R.id.txt_drink_amount);
            txt_sugar_ice = itemView.findViewById(R.id.txt_sugar_ice);
            txt_size = itemView.findViewById(R.id.txt_size);
            txt_topping = itemView.findViewById(R.id.txt_topping);
            txt_drink_name = itemView.findViewById(R.id.txt_drink_name);


        }
    }
}
