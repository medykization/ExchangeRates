package com.example.exchange_rates.data;

public class Currency {

    private String code;
    private String currencyName;
    private double midValue;

    public Currency(String code, String currencyName, double midValue) {
        this.code = code;
        this.currencyName = currencyName;
        this.midValue = midValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMidValue() {
        return midValue;
    }

    public void setMidValue(double midValue) {
        this.midValue = midValue;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return code + " " + currencyName + " " + "1" + code + " = " + midValue;
    }
}
