package com.example.myapplication;

import com.example.myapplication.Entities.Coin;
import com.example.myapplication.Entities.CoinLoreResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinService {
    @GET("api/tickers/")
    Call<CoinLoreResponse> getAllCoins();

}
