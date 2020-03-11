package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG ="MyAdapter";

    ArrayList<Coin> myArray;
    RecyclerViewClickListener listener;

    public MyAdapter(ArrayList<Coin> myArray, RecyclerViewClickListener listener) {
        this.myArray = myArray;
        this.listener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_row, parent, false);
        return new MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NumberFormat coinCurrency = NumberFormat.getCurrencyInstance();

        holder.coinName.setText(myArray.get(position).getName());
        String coinValueS = coinCurrency.format(myArray.get(position).getValue());
        holder.coinPrice.setText(coinValueS);
        holder.percentChange.setText(myArray.get(position).getChange1h()+" %");
    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Declare the views from the row xml
        ImageView coinImage;
        TextView coinName;
        TextView coinPrice;
        TextView percentChange;
        RecyclerViewClickListener listener;

        //Constructor
        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            //set a listener to the view (i.e setting a click listener to every row of the recyclerView)
            itemView.setOnClickListener(this);

            //initialise views
            this.listener = listener;
            this.coinImage = itemView.findViewById(R.id.imageView);
            this.coinName = itemView.findViewById(R.id.coinName);
            this.coinPrice = itemView.findViewById(R.id.coinPrice);
            this.percentChange = itemView.findViewById(R.id.percentChange);

        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: inside myadapter on click");
            listener.onClick(v, getAdapterPosition());
        }
    }
}
