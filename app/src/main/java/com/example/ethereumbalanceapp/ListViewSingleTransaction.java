package com.example.ethereumbalanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListViewSingleTransaction extends AppCompatActivity {

    ListView lv_singleTransactionReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_single_transaction);

        lv_singleTransactionReport = findViewById(R.id.lv_singleTransactionReports);

        Intent intent = getIntent();

        BlockchainService blockchainService = new BlockchainService(ListViewSingleTransaction.this);

        blockchainService.getSingleTransactionByHash(intent.getStringExtra(MainActivity.et_data), new BlockchainService.GetSingleTransactionByHashResponse() {
            @Override
            public void onError(String message) {
                Toast.makeText(ListViewSingleTransaction.this,  message , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(List<SingleTransactionModel> nonceTransactionModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(ListViewSingleTransaction.this, android.R.layout.simple_list_item_1, nonceTransactionModels);
                lv_singleTransactionReport.setAdapter(arrayAdapter);
            }
        });
    }
}