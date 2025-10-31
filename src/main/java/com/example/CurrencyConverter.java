package com.example.todoapp.service;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;

public class CurrencyConverter {

    public static MonetaryAmount converterEuroParaDolar(double valorEuro, double taxaCambio) {
        MonetaryAmount euros = Money.of(valorEuro, "EUR");
        double valorConvertido = valorEuro * taxaCambio;
        MonetaryAmount dolares = Money.of(valorConvertido, "USD");
        return dolares;
    }

    public static void main(String[] args) {
        MonetaryAmount resultado = converterEuroParaDolar(100, 1.08);
        System.out.println("100 EUR equivalem a " + resultado);
    }
}
