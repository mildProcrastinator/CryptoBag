package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    boolean inWide;

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    /**Creating data set to populate list*/
    ArrayList<Coin> coins = Coin.getCoins();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coins = Coin.getCoins();
        Log.d(TAG, "onCreate: made array");

        recyclerView = (RecyclerView) findViewById(R.id.coin_list_view);
        recyclerView.setHasFixedSize(true);
        Log.d(TAG, "onCreate: initialised up recyclerview");

        /**creating and setting linear layout manager*/
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.d(TAG, "onCreate: layout manager made");

        inWide = findViewById(R.id.detail_fragment) != null;

        MyAdapter.RecyclerViewClickListener listener = new MyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(inWide) {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    Fragment fragment = new coinListFragment();
                    Bundle bundle = new Bundle();
                    //bundle.putBoolean("inWide", inWide);
                    bundle.putString("cName", coins.get(position).getName());
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.detail_fragment, fragment);
                    transaction.commit();
                }else{
                    changeActivities(view, coins.get(position).getName());
                }

            }
        };

        Log.d(TAG, "onCreate: made listener");

        mAdapter = new MyAdapter(coins, listener);
        recyclerView.setAdapter(mAdapter);
    }

    /**called when user taps a coin*/
    public void changeActivities(View view, String coin) {

        Log.d(TAG, "clickResponse: pressed " + coin);
        Intent explicitIntent = new Intent(this, DetailPage.class);
        explicitIntent.putExtra("currency",coin);
        startActivity(explicitIntent);

    }
}
