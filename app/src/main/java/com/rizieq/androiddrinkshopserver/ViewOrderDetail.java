package com.rizieq.androiddrinkshopserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rizieq.androiddrinkshopserver.adapter.OrderDetailAdapter;
import com.rizieq.androiddrinkshopserver.utils.Common;

public class ViewOrderDetail extends AppCompatActivity {

    TextView txt_order_id,txt_order_price,txt_order_comment,txt_order_address;
    Spinner spinner_order_status;
    RecyclerView recycler_order_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);

        txt_order_id= findViewById(R.id.txt_order_id);
        txt_order_price= findViewById(R.id.txt_order_price);
        txt_order_comment = findViewById(R.id.txt_order_comment);
        txt_order_address = findViewById(R.id.txt_order_address);

        spinner_order_status = findViewById(R.id.spinner_order_id);

        recycler_order_detail = findViewById(R.id.recycler_order_detail);
        recycler_order_detail.setLayoutManager(new LinearLayoutManager(this));
        recycler_order_detail.setAdapter(new OrderDetailAdapter(this));

        txt_order_id.setText(new StringBuilder("#")
        .append(Common.currentOrder.getOrderId()));
        txt_order_price.setText(new StringBuilder("$").append(Common.currentOrder.getOrderPrice()));
        txt_order_address.setText(Common.currentOrder.getOrderAddress());
        txt_order_comment.setText(Common.currentOrder.getOrderComment());


    }
}
