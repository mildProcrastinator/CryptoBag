package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Entities.Coin;

import java.text.NumberFormat;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG ="MyAdapter";
    private MainActivity mParentActivity;
    private List<Coin> mCoins;
    private boolean inWide;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Coin coin = (Coin) v.getTag();
            if(inWide) {
                Bundle arguments = new Bundle();
                arguments.putString(DetailFragment.ARG_ITEM_ID, coin.getId());
                DetailFragment fragment = new DetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment, fragment).commit();
            }else {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailFragment.ARG_ITEM_ID, coin.getId());
                context.startActivity(intent);
            }
        }
    };

    public MyAdapter(MainActivity parent, List<Coin> coins, boolean twoPane) {
        mParentActivity = parent;
        mCoins = coins;
        inWide = twoPane;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //Declare the views from the row xml
        //ImageView coinImage;
        TextView coinName;
        TextView coinPrice;
        TextView percentChange;

        //Constructor
        public MyViewHolder(View itemView) {
            super(itemView);

            //initialise views
            //this.coinImage = itemView.findViewById(R.id.imageView);
            coinName = itemView.findViewById(R.id.coinName);
            coinPrice = itemView.findViewById(R.id.coinPrice);
            percentChange = itemView.findViewById(R.id.percentChange);

        }
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Coin coin = mCoins.get(position);
        NumberFormat coinCurrency = NumberFormat.getCurrencyInstance();

        holder.coinName.setText(coin.getName());
        String coinValueS = coinCurrency.format(Double.valueOf(coin.getPriceUsd()));
        holder.coinPrice.setText(coinValueS);
        holder.percentChange.setText(coin.getPercentChange24h()+" %");
        holder.itemView.setTag(coin);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mCoins.size();
    }

}
