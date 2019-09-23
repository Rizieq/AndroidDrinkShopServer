package com.rizieq.androiddrinkshopserver.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizieq.androiddrinkshopserver.Interface.IItemClickListener;
import com.rizieq.androiddrinkshopserver.R;
import com.rizieq.androiddrinkshopserver.ViewOrderDetail;
import com.rizieq.androiddrinkshopserver.adapter.viewHolder.OrderViewHolder;
import com.rizieq.androiddrinkshopserver.model.Order;
import com.rizieq.androiddrinkshopserver.utils.Common;

import java.util.List;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    Context context;
    List<Order> orderList;

    public OrderViewAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.order_layout, viewGroup, false);

        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, final int i) {

        orderViewHolder.txt_order_id.setText(new StringBuilder("#").append(orderList.get(i).getOrderId())
                .toString());
        orderViewHolder.txt_order_price.setText(new StringBuilder("$").append(orderList.get(i).getOrderPrice()));

        if (orderList.get(i).getOrderComment() != null &&
        !orderList.get(i).getOrderComment().isEmpty()){

            orderViewHolder.txt_order_comment.setText(new StringBuilder("Comment : ")
            .append(orderList.get(i).getOrderComment()));
        }
        else
        {
            orderViewHolder.txt_order_comment.setText(new StringBuilder("Comment : ")
                    .append("None"));
        }
        orderViewHolder.txt_order_status.setText(new StringBuilder("Order Status : ").append(Common.convertCodeToStatus(orderList.get(i).getOrderStatus())));

        orderViewHolder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, boolean isLongClick) {

                Common.currentOrder = orderList.get(i);

                context.startActivity(new Intent(context, ViewOrderDetail.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
