package com.kmush12.CurrencyConverter.exchange;

import com.kmush12.CurrencyConverter.currencylist.ExternalAvailableCurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${getcurrencydata.url}", name = "CURRENCY-RATE-CLIENT")
public interface ExternalExchangeRateClient {

    @GetMapping("/symbols")
    ExternalAvailableCurrency getExternalAvailableCurrency();

    @GetMapping("/latest?base={originCurrency}")
    ExternalExchangeRate getExternalRate(@PathVariable String originCurrency);

}
