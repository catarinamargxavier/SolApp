package com.company.my.solapp.others;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.company.my.solapp.R;
import com.company.my.solapp.data.Prevision;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {

    private ArrayList<Prevision> dataset;
    private PrevisionViewModel previsionViewModel;
    private Activity activity;


    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tempMax;
        TextView tempMin;
        TextView wind;
        TextView probRain;
        TextView description;
        TextView date;

        public DataObjectHolder(View itemView) {
            super(itemView);
            tempMax = (TextView) itemView.findViewById(R.id.textViewTempMax);
            tempMin = (TextView) itemView.findViewById(R.id.textViewTempMin);
            wind = (TextView) itemView.findViewById(R.id.textViewWind);
            probRain = (TextView) itemView.findViewById(R.id.textViewProbRain);
            description = (TextView) itemView.findViewById(R.id.textViewDescription);
            date = (TextView) itemView.findViewById(R.id.textViewDatePrevision);
        }
    }


    public MyRecyclerViewAdapter(ArrayList<Prevision> myDataset, Activity activity) {
        dataset = myDataset;
        this.activity = activity;
        previsionViewModel = ViewModelProviders.of((FragmentActivity) activity).get(PrevisionViewModel.class);
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_row, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }


    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        int idWeather = dataset.get(position).getIdType();
        previsionViewModel.getWeatherClassification(idWeather).observe((LifecycleOwner) activity, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    holder.description.setText(s);
                }
            }
        });
        int idWind = dataset.get(position).getClassWindSpeed();
        previsionViewModel.getWindClassification(idWind).observe((LifecycleOwner) activity, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null) {
                    holder.wind.setText(s);
                }
            }
        });
        holder.tempMax.setText(dataset.get(position).getTempMax() + "º");
        holder.tempMin.setText(dataset.get(position).getTempMin() + "º");
        holder.probRain.setText(dataset.get(position).getProbRain() + " %");
        holder.date.setText(dataset.get(position).getDate());
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

}

