package com.example.exchange_rates.data;

public class CurrencyExtraData {

    private String code;
    private String currency;
    private double sellPrice;
    private double buyPrice;

    public CurrencyExtraData(String code, String currency, double sellPrice, double buyPrice) {
        this.code = code;
        this.currency = currency;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

}
