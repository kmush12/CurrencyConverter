package com.kmush12.CurrencyConverter.Converter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/convert")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping("{fromCode}/{toCode}/{amount}")
    public void getToCurrencyAmount(HttpServletResponse response, @PathVariable String fromCode, @PathVariable String toCode, @PathVariable BigDecimal amount) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print(currencyConverterService.createExchange(fromCode, toCode, amount).getToCodeAmount());
    }

}

