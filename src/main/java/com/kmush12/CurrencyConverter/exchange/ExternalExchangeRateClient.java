package com.kmush12.CurrencyConverter.exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${getcurrencydata.url}", name = "CURRENCY-RATE-CLIENT")
public interface ExternalExchangeRateClient {

    @GetMapping("/symbols")
    ExternalAvailableCurrency getExternalAvailableCurrency();

    @GetMapping("/latest?base={originCurrency}&symbols={destinationCurrency}")
    ExternalExchangeRate getExternalRate(@PathVariable String originCurrency, @PathVariable String destinationCurrency);

}
