package com.kmush12.CurrencyConverter.currencylist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public record ExternalAvailableCurrency(@JsonIgnore Motd motd, boolean success, Map<String, CurrencyDescription>symbols){
}
