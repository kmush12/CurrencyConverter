package com.kmush12.CurrencyConverter.currencylist;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public record AvailableCurrency(@JsonIgnore boolean success, Map<String, CurrencyDescription>symbols){
}
