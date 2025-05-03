package main.java.com.conversor.model;

import java.util.Collections;
import java.util.Map;

/**
 * Representa a resposta da API de taxas de câmbio.
 * Contém o resultado da operação e um mapa com as taxas de conversão.
 */
public class ExchangeRateResponse {
    private final String result;
    public final Map<String, Double> conversion_rates;

    public ExchangeRateResponse(String result, Map<String, Double> conversion_rates) {
        this.result = result;
        this.conversion_rates = Collections.unmodifiableMap(conversion_rates);
    }

    /**
     * @return O status do resultado da operação
     */
    public String getResult() {
        return result;
    }

    /**
     * @return Mapa imutável com as taxas de câmbio (moeda → taxa)
     */
    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    /**
     * Verifica se a resposta indica sucesso
     * @return true se o resultado for "success"
     */
    public boolean isSuccess() {
        return "success".equalsIgnoreCase(result);
    }
}