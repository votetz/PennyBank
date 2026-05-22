package models;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private static final Map<Currency, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put(Currency.USD, 1.0);
        exchangeRates.put(Currency.EUR, 0.92);
        exchangeRates.put(Currency.UAH, 40.0);
    }

    public static double convert(double amount, Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == toCurrency) {
            return amount;
        }

        double amountInUSD = amount / exchangeRates.get(fromCurrency);
        return amountInUSD * exchangeRates.get(toCurrency);
    }

    public static double getRate(Currency currency) {
        return exchangeRates.get(currency);
    }
}
