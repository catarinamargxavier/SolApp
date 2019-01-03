package com.company.my.solapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.company.my.solapp.R;
import com.company.my.solapp.data.Prevision;
import com.company.my.solapp.others.MyRecyclerViewAdapter;
import com.company.my.solapp.others.PrevisionViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private PrevisionViewModel previsionViewModel;
    private String localName;
    private int localId;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Prevision> previsions;
    private TextView local;
    private TextView lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        previsionViewModel = ViewModelProviders.of(this).get(PrevisionViewModel.class);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        lastUpdate = (TextView) findViewById(R.id.textViewLastUpdate);
        local = (TextView) findViewById(R.id.textViewLocal);

        previsions = new ArrayList<>();
        show();

        localName = getIntent().getStringExtra("place");

        previsionViewModel.getIdLocal(localName).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null) {
                    localId = (int) integer;
                    getPrevisions();
                }
            }
        });

        local.setText(localName);

    }


    public void show() {
        adapter = new MyRecyclerViewAdapter(previsions, WeatherActivity.this);
        recyclerView.setAdapter(adapter);
    }


    private void getPrevisions() {
        int i = 0;
        for (Date date: getDates()) {
            int finalI = i;
            previsionViewModel.getPrevision(localId,  new SimpleDateFormat("yyyy-MM-dd").format(date)).observe(this, new Observer<Prevision>() {
                @Override
                public void onChanged(@Nullable Prevision prevision) {
                    if (prevision !=  null) {
                        if (previsions.indexOf(prevision) != -1) {
                            previsions.remove(prevision);
                        }
                        previsions.add(prevision);
                        show();
                    }
                }
            });
            i++;
        }
    }


    private Date[] getDates () {
        Date[] aux = new Date[5];
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        aux[0] = cal.getTime();
        for (int i = 0; i < 4; i++) {
            cal.add(Calendar.DATE, 1);
            aux[i+1] = cal.getTime();
        }
        return aux;
    }


}
