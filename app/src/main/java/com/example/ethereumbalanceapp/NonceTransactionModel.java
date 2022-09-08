package com.example.ethereumbalanceapp;

public class NonceTransactionModel {
    private String blockNumber;
    private String nonce;
    private String from;
    private String to;
    private String gas;
    private String gasPrice;
    private String gasUsed;
    private String cumulativeGasUsed;
    private String contractAddress;


    public NonceTransactionModel() {
    }

    @Override
    public String toString() {
        return "Block Number: " + blockNumber + "\n"
                + "Nonce: " + nonce + "\n"
                + "From: " + from + "\n"
                + "To: " + to + "\n"
                + "Gas: " + gas + "\n"
                + "Gas Price: " + gasPrice + "\n"
                + "Gas Used: " + gasUsed + "\n"
                + "Cumulative Gas Used: " + cumulativeGasUsed + "\n"
                + "Contract Address: " + contractAddress;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
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

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public void setCumulativeGasUsed(String cumulativeGasUsed) {
        this.cumulativeGasUsed = cumulativeGasUsed;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
