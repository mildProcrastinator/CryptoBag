package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link coinListFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class coinListFragment extends Fragment {


    public coinListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        String cName = getArguments().getString("cName");

        Coin displayedCoin = Coin.returnCoin(cName);

        NumberFormat coinCurrency = NumberFormat.getCurrencyInstance();

        View v = inflater.inflate(R.layout.fragment_coin_list, container, false);
        TextView coinName = v.findViewById(R.id.coinName);
        TextView coinCode = v.findViewById(R.id.coinCode);
        TextView coinValue = v.findViewById(R.id.coinValue);
        TextView change1h = v.findViewById(R.id.change1h);
        TextView change24h = v.findViewById(R.id.change24h);
        TextView change7d = v.findViewById(R.id.change7d);
        TextView marketCap = v.findViewById(R.id.marketcap);
        TextView volume24h = v.findViewById(R.id.volume);

        Button searchButton = (Button)v.findViewById(R.id.button2);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cName = getArguments().getString("cName");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q="+cName));
                startActivity(intent);
            }
        });

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

        return v;
    }

}
