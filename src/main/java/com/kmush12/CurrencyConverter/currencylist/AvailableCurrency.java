package com.kmush12.CurrencyConverter.currencylist;


import java.util.Map;

public record AvailableCurrency(boolean success, Map<String, CurrencyDescription>symbols){
}
