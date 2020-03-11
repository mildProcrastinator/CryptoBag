package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class DetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String message = (String) extras.get("currency");


        Coin displayedCoin = Coin.returnCoin(message);

        TextView coinName = findViewById(R.id.coinName);
        TextView coinCode = findViewById(R.id.coinCode);
        TextView coinValue = findViewById(R.id.coinValue);
        TextView change1h = findViewById(R.id.change1h);
        TextView change24h = findViewById(R.id.change24h);
        TextView change7d = findViewById(R.id.change7d);
        TextView marketCap = findViewById(R.id.marketcap);
        TextView volume24h = findViewById(R.id.volume);

        NumberFormat coinCurrency = NumberFormat.getCurrencyInstance();

        coinName.setText(displayedCoin.getName());
        coinCode.setText(displayedCoin.getSymbol());
        String coinValueS = coinCurrency.format(displayedCoin.getValue());
        coinValue.setText(coinValueS);
        change1h.setText(displayedCoin.getChange1h()+" %");
        change24h.setText(displayedCoin.getChange24h()+" %");
        change7d.setText(displayedCoin.getChange7d()+" %");

        String marketCapS = coinCurrency.format(displayedCoin.getMarketcap()/1000000000);
        marketCap.setText(marketCapS+" bn");
        String volumeS = coinCurrency.format(displayedCoin.getVolume()/1000000000);
        volume24h.setText(volumeS +" bn");


    }

    public void searchCoin(View view) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String currency = (String) extras.get("currency");
        Intent implicitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q="+currency));
        startActivity(implicitIntent);
    }
}
