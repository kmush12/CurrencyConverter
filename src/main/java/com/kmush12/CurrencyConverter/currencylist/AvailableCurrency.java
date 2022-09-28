package com.kmush12.CurrencyConverter.currencylist;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Objects;

@Document
public final class AvailableCurrency {
    @Id
    private String id;
    @JsonIgnore
    private final boolean success;
    private final Map<String, CurrencyDescription> symbols;

    public AvailableCurrency(boolean success, Map<String, CurrencyDescription> symbols) {
        this.success = success;
        this.symbols = symbols;
    }

    public Map<String, CurrencyDescription> symbols() {
        return symbols;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AvailableCurrency) obj;
        return Objects.equals(this.id, that.id) &&
                this.success == that.success &&
                Objects.equals(this.symbols, that.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, success, symbols);
    }

    @Override
    public String toString() {
        return "AvailableCurrency[" +
                "id=" + id + ", " +
                "success=" + success + ", " +
                "symbols=" + symbols + ']';
    }

}
