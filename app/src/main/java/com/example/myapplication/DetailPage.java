package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        String coinName = (String) extras.get("currency");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new coinListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cName", coinName);
        fragment.setArguments(bundle);
        transaction.replace(R.id.detail_fragment, fragment);
        transaction.commit();


    }

}
