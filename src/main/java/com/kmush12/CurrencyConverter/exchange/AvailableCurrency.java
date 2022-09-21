package com.kmush12.CurrencyConverter.exchange;


import java.util.Map;

public record AvailableCurrency( boolean success, Map<String, CurrencyDescription>symbols){
}
