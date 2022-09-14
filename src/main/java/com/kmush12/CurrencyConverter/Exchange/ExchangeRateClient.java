package com.kmush12.CurrencyConverter.Exchange;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${getcurrencydata.url}", name = "CURRENCY-RATE-CLIENT")
public interface ExchangeRateClient {

    @GetMapping("/symbols")
    JsonNode getListOfCurrency();

    @GetMapping("/latest?base={originCurrency}&symbols={destinationCurrency}")
    ExchangeRate getRateFromAPI(@PathVariable String originCurrency, @PathVariable String destinationCurrency);

}
