package com.kmush12.CurrencyConverter.currencylist;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Objects;

@Document
public final class AvailableCurrency {
    @Id
    private String id;
    private final Map<String, CurrencyDescription> symbols;

    public AvailableCurrency(Map<String, CurrencyDescription> symbols) {
        this.symbols = symbols;
    }

    public Map<String, CurrencyDescription> symbols() {
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableCurrency that = (AvailableCurrency) o;
        return Objects.equals(id, that.id) && Objects.equals(symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbols);
    }

    @Override
    public String toString() {
        return "AvailableCurrency{" +
                "id='" + id + '\'' +
                ", symbols=" + symbols +
                '}';
    }
}
