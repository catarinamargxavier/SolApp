package com.company.my.solapp.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.company.my.solapp.R;
import com.company.my.solapp.data.Local;
import com.company.my.solapp.data.Prevision;
import com.company.my.solapp.others.MyRecyclerViewAdapter;
import com.company.my.solapp.others.PrevisionViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PrevisionViewModel previsionViewModel;
    private Spinner spinnerLocal;
    private Button buttonSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previsionViewModel = ViewModelProviders.of(this).get(PrevisionViewModel.class);
        spinnerLocal = (Spinner) findViewById(R.id.spinnerLocal);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);

        fillSpinner();
        fillDatabase();

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerLocal.getSelectedItem() == null) {
                    if (!checkNetwork()) {
                        Toast.makeText(

                                MainActivity.this,
                                "É necessário uma ligação à internet para baixar os dados!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (!checkNetwork()) {
                        sendWarningAndLoad();
                    } else {
                        load();
                    }
                }
            }
        });
    }


    private void fillDatabase() {
        previsionViewModel.getAllPrevisionData();
    }


    private void sendWarningAndLoad() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Parece que não há uma ligação à internet! Poderão ser apresentados " +
                        "dados desatualizados.")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        load();
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void load() {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        intent.putExtra("place", spinnerLocal.getSelectedItem().toString());
        startActivity(intent);
    }


    private boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


    private void fillSpinner() {
        previsionViewModel.getAllLocal().observe(this, new Observer<List<Local>>() {
            @Override
            public void onChanged(@Nullable List<Local> locals) {
                fillDatabase();
                if (locals != null) {
                    List<String> aux = new ArrayList<>();
                    for (Local local : locals) {
                        aux.add(local.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, aux);
                    adapter.setDropDownViewResource(android.R.layout
                            .simple_spinner_dropdown_item);
                    spinnerLocal.setAdapter(adapter);
                }
            }
        });
    }

}
