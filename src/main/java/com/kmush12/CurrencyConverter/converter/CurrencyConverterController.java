package com.kmush12.CurrencyConverter.converter;

import com.kmush12.CurrencyConverter.exchange.ExchangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/converter")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }


    @ResponseBody
    @GetMapping
    public ResponseEntity convert(@RequestBody ExchangeRequest exchangeRequest) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(currencyConverterService.converted(exchangeRequest));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/currency-list")
    public ResponseEntity currencyList() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(currencyConverterService.getCurrencyList());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

