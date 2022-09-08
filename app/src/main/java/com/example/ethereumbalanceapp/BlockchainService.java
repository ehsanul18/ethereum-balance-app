package com.example.ethereumbalanceapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BlockchainService {
    public static final String QUERY_TO_CHECK_BALANCE = "https://api.etherscan.io/api?module=account&action=balance&address=";
    public static final String QUERY_TO_CHECK_NONCE_TRANSACTION = "https://api.etherscan.io/api?module=account&action=txlist&address=";
    public static final String QUERY_TO_CHECK_SINGLE_TRANSACTION = "https://api.etherscan.io/api?module=account&action=txlistinternal&txhash=";

    Context context;
    String balance;

    public BlockchainService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String balance);
    }

    public void getPublicAddress(String public_address, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_TO_CHECK_BALANCE + public_address + "&tag=latest&apikey=BU3B6YDJFG3Z6TW2DK5P9KAY2X85FZ2972";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                balance = "";
                try {
                    balance = response.getString("result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(balance);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface GetNonceTransactionByAddressResponse {
        void onError(String message);

        void onResponse(List<NonceTransactionModel> nonceTransactionModels);
    }

    public void getNonceTransactionByAddress(String public_address, GetNonceTransactionByAddressResponse getNonceTransactionByAddress) {
        List<NonceTransactionModel> nonceTransactionModels = new ArrayList<>();

        String url = QUERY_TO_CHECK_NONCE_TRANSACTION + public_address + "&startblock=0&endblock=99999999&page=1&offset=10&sort=asc&apikey=BU3B6YDJFG3Z6TW2DK5P9KAY2X85FZ2972";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray transaction_array = response.getJSONArray("result");
                    for (int i = 0; i < transaction_array.length(); i++) {
                        NonceTransactionModel transactionModel = new NonceTransactionModel();
                        JSONObject transaction_parameter = (JSONObject) transaction_array.get(i);
                        transactionModel.setBlockNumber(transaction_parameter.getString("blockNumber"));
                        transactionModel.setNonce(transaction_parameter.getString("nonce"));
                        transactionModel.setFrom(transaction_parameter.getString("from"));
                        transactionModel.setTo(transaction_parameter.getString("to"));
                        transactionModel.setGas(transaction_parameter.getString("gas"));
                        transactionModel.setGasPrice(transaction_parameter.getString("gasPrice"));
                        transactionModel.setGasUsed(transaction_parameter.getString("gasUsed"));
                        transactionModel.setCumulativeGasUsed(transaction_parameter.getString("cumulativeGasUsed"));
                        transactionModel.setContractAddress(transaction_parameter.getString("contractAddress"));
                        nonceTransactionModels.add(transactionModel);
                    }
                    getNonceTransactionByAddress.onResponse(nonceTransactionModels);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getNonceTransactionByAddress.onError("Something went wrong while retrieving multiple transactions!");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }


    public interface GetSingleTransactionByHashResponse {
        void onError(String message);

        void onResponse(List<SingleTransactionModel> nonceTransactionModels);
    }

    public void getSingleTransactionByHash(String transaction_hash, GetSingleTransactionByHashResponse getSingleTransactionByHashResponse) {
        List<SingleTransactionModel> singleTransactionModels = new ArrayList<>();

        String url = QUERY_TO_CHECK_SINGLE_TRANSACTION + transaction_hash + "&apikey=BU3B6YDJFG3Z6TW2DK5P9KAY2X85FZ2972";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray transaction_array = response.getJSONArray("result");
                    SingleTransactionModel transactionModel = new SingleTransactionModel();
                    JSONObject transaction_parameter = (JSONObject) transaction_array.get(0);
                    transactionModel.setBlockNumber(transaction_parameter.getString("blockNumber"));
                    transactionModel.setValue(transaction_parameter.getString("value"));
                    transactionModel.setFrom(transaction_parameter.getString("from"));
                    transactionModel.setTo(transaction_parameter.getString("to"));
                    transactionModel.setGas(transaction_parameter.getString("gas"));
                    transactionModel.setGasUsed(transaction_parameter.getString("gasUsed"));
                    transactionModel.setContractAddress(transaction_parameter.getString("contractAddress"));
                    singleTransactionModels.add(transactionModel);
                    getSingleTransactionByHashResponse.onResponse(singleTransactionModels);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getSingleTransactionByHashResponse.onError("Something went wrong while retrieving single transaction!");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
