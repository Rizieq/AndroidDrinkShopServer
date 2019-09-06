package com.rizieq.androiddrinkshopserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rizieq.androiddrinkshopserver.adapter.DrinkListAdapter;
import com.rizieq.androiddrinkshopserver.model.Drink;
import com.rizieq.androiddrinkshopserver.retrofit.IDrinkshopAPI;
import com.rizieq.androiddrinkshopserver.utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DrinkListActivity extends AppCompatActivity {

    IDrinkshopAPI mService;
    RecyclerView recycler_drinks;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);


        mService = Common.getAPI();


        recycler_drinks = findViewById(R.id.recycler_drinks);
        recycler_drinks.setLayoutManager(new GridLayoutManager(this, 2));
        recycler_drinks.setHasFixedSize(true);

        loadListDrink(Common.currentCategory.getID());

    }

    private void loadListDrink(String id) {
        compositeDisposable.add(mService.getDrink(id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Drink>>() {
                    @Override
                    public void accept(List<Drink> drinks) throws Exception {

                        displayDrinkList(drinks);
                    }
                }));
    }

    private void displayDrinkList(List<Drink> drinks) {

        DrinkListAdapter adapter = new DrinkListAdapter(this,drinks);
        recycler_drinks.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        loadListDrink(Common.currentCategory.getID());
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
