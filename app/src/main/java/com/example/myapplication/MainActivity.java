package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.Entities.Coin;
import com.example.myapplication.Entities.CoinLoreResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    boolean inWide;
    List<Coin> coins = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starting");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inWide = findViewById(R.id.detail_fragment) != null;

        RecyclerView recyclerView = findViewById(R.id.coin_list_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Coin> list = new ArrayList();

        RecyclerView.Adapter mAdapter = new MyAdapter(this, list, inWide);
        recyclerView.setAdapter(mAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coinlore.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoinService service = retrofit.create(CoinService.class);

        Call<CoinLoreResponse> coinCall = service.getAllCoins();

        coinCall.enqueue(new Callback<CoinLoreResponse>() {
            @Override
            public void onResponse(Call<CoinLoreResponse> call, Response<CoinLoreResponse> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "onResponse: SUCCESSFUL");
                    coins = response.body().getData();
                    DetailFragment.updateCoins(coins);
                    ((MyAdapter) mAdapter).setCoins(coins);
                    recyclerView.setAdapter(mAdapter);
                }else{
                    Log.d(TAG, "onResponse: ERROR IS: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CoinLoreResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ON FAILURE IS: "+t.getLocalizedMessage());
            }
        });



    }


}
