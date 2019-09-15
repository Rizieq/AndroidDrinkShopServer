package com.rizieq.androiddrinkshopserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rizieq.androiddrinkshopserver.adapter.OrderDetailAdapter;
import com.rizieq.androiddrinkshopserver.retrofit.IDrinkshopAPI;
import com.rizieq.androiddrinkshopserver.utils.Common;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ViewOrderDetail extends AppCompatActivity {

    TextView txt_order_id,txt_order_price,txt_order_comment,txt_order_address;
    Spinner spinner_order_status;
    RecyclerView recycler_order_detail;

    // DECLARE VALUE FOR SPINNER
    String[] spinner_source = new String[]{
            "Cancelled", // Index 0
            "Placed", // Index 1
            "Processed", // Index 2
            "Shipping", // Index 3
            "Shipped", // Index4
    };

    IDrinkshopAPI mService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mService = Common.getAPI();

        txt_order_id= findViewById(R.id.txt_order_id);
        txt_order_price= findViewById(R.id.txt_order_price);
        txt_order_comment = findViewById(R.id.txt_order_comment);
        txt_order_address = findViewById(R.id.txt_order_address);

        spinner_order_status = findViewById(R.id.spinner_order_id);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                spinner_source);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_order_status.setAdapter(spinnerArrayAdapter);

        recycler_order_detail = findViewById(R.id.recycler_order_detail);
        recycler_order_detail.setLayoutManager(new LinearLayoutManager(this));
        recycler_order_detail.setAdapter(new OrderDetailAdapter(this));

        txt_order_id.setText(new StringBuilder("#")
        .append(Common.currentOrder.getOrderId()));
        txt_order_price.setText(new StringBuilder("$").append(Common.currentOrder.getOrderPrice()));
        txt_order_address.setText(Common.currentOrder.getOrderAddress());
        txt_order_comment.setText(Common.currentOrder.getOrderComment());


        setSpinnerSelectedBaseOnOrder();

    }

    private void setSpinnerSelectedBaseOnOrder() {
        switch (Common.currentOrder.getOrderStatus())
        {
            case -1:
                spinner_order_status.setSelection(0); // CANCELLED
                break;

            case 0:
                spinner_order_status.setSelection(1); // PLACED
                break;

            case 1:
                spinner_order_status.setSelection(2); // PROCESSED
                break;

            case 2:
                spinner_order_status.setSelection(3); // SHIPPING
                break;

            case 3:
                spinner_order_status.setSelection(4); // SHIPPED
                break;

        }
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save_order_detail)
            saveUpdateOrder();
        return true;
    }

    private void saveUpdateOrder() {
        int order_status = spinner_order_status.getSelectedItemPosition()-1;
        Log.d("ORDER_STATUS ", String.valueOf(Common.currentOrder.getOrderPhone()));
        compositeDisposable.add(mService.updateOrderStatus(Common.currentOrder.getOrderPhone(),
                Common.currentOrder.getOrderId(),
                order_status)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                Toast.makeText(ViewOrderDetail.this, "Order updated!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("ERROR ",""+throwable.getMessage());
            }
        }));
    }
}
