package com.example.ethereumbalanceapp;

import java.math.BigDecimal;

public class SingleTransactionModel {
    private String blockNumber;
    private String value;
    private String from;
    private String to;
    private String gas;
    private String gasUsed;
    private String contractAddress;


    public SingleTransactionModel() {
    }

    @Override
    public String toString() {
        return "Block Number: " + blockNumber + "\n"
                + "Value: " + value + " ETH\n"
                + "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Gas: " + gas + "\n"
                + "Gas Used: " + gasUsed + "\n"
                + "Contract Address: " + contractAddress;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        BigDecimal calc = new BigDecimal(value);
        BigDecimal ten = new BigDecimal("1000000000000000000");
        calc = calc.divide(ten);
        String result = String.valueOf(calc);
        this.value = result;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
