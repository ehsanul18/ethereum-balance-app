package com.example.ethereumbalanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Button btn_checkBalance, btn_showNonceTransaction, btn_checkSingleTransaction;
    EditText et_dataInput, et_transactionInput;
    public static final String et_data = "";
    public static final String et_single_transaction = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to each control of the layout
        btn_checkBalance = findViewById(R.id.btn_checkBalance);
        btn_showNonceTransaction = findViewById(R.id.btn_NonceTransaction);
        btn_checkSingleTransaction = findViewById(R.id.btn_CheckSingleTransaction);

        et_dataInput = findViewById(R.id.et_dataInput);
        et_transactionInput = findViewById(R.id.et_transactionInput);



        BlockchainService blockchainService = new BlockchainService(MainActivity.this);

        btn_checkBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockchainService.getPublicAddress(et_dataInput.getText().toString(), new BlockchainService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,  message , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String balance) {
                        BigDecimal calc = new BigDecimal(balance);
                        BigDecimal ten = new BigDecimal("1000000000000000000");
                        calc = calc.divide(ten);
                        String result = String.valueOf(calc);
                        Toast.makeText(MainActivity.this, "Current balance is " + result + " ETH", Toast.LENGTH_LONG).show();
                        //0xde0B295669a9FD93d5F28D9Ec85E40f4cb697BAe
                    }
                });
            }
        });

        btn_showNonceTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewTransaction.class);
                String message = et_dataInput.getText().toString();
                intent.putExtra(et_data, message);
                startActivity(intent);
            }
        });

        btn_checkSingleTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewSingleTransaction.class);
                String message = et_transactionInput.getText().toString();
                intent.putExtra(et_single_transaction, message);
                startActivity(intent);
            }
        });
    }
}