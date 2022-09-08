package com.example.ethereumbalanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListViewTransaction extends AppCompatActivity {

    ListView lv_transactionReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_transaction);

        lv_transactionReports = findViewById(R.id.lv_transactionReports);

        Intent intent = getIntent();

        BlockchainService blockchainService = new BlockchainService(ListViewTransaction.this);

        blockchainService.getNonceTransactionByAddress(intent.getStringExtra(MainActivity.et_data), new BlockchainService.GetNonceTransactionByAddressResponse() {
            @Override
            public void onError(String message) {
                Toast.makeText(ListViewTransaction.this,  message , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(List<NonceTransactionModel> nonceTransactionModels) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(ListViewTransaction.this, android.R.layout.simple_list_item_1, nonceTransactionModels);
                lv_transactionReports.setAdapter(arrayAdapter);
            }
        });
    }
}