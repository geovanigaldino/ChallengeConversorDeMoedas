import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversorDeMoedas {
    private static final String API_KEY = "993b092558eec2482218142a";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final Map<String, Double> CACHE = new HashMap<>();
    private List<String> historico = new ArrayList<>();

    private static final String USD = "USD";
    private static final String ARS = "ARS";
    private static final String BRL = "BRL";
    private static final String COP = "COP";
    private static final String GBP = "GBP";
    private ConversorDeMoedas conversor;

    public double getTaxaDeCambio(String moeda) {
        if (CACHE.containsKey(moeda)) {
            return CACHE.get(moeda);
        }

        try {
            String url = BASE_URL + API_KEY + "/latest/" + USD;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                ExchangeRateResponse apiResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);
                Double taxa = apiResponse.conversion_rates.get(moeda);

                if (taxa == null) {
                    System.err.println("Moeda não encontrada: " + moeda);
                    return 0;
                }

                CACHE.put(moeda, taxa);
                return taxa;
            } else {
                System.err.println("Erro na API: " + response.statusCode());
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Falha na requisição: " + e.getMessage());
            return 0;
        }
    }

    public double obterTaxaEntreMoedas(String origem, String destino) {
        double taxaOrigem = getTaxaDeCambio(origem);
        double taxaDestino = getTaxaDeCambio(destino);

        if (taxaOrigem == 0 || taxaDestino == 0) {
            return 0;
        }

        return taxaDestino / taxaOrigem;
    }

    public String converter(int opcao, double valor) {
        // Validação inicial
        if (opcao == 9) {
            exibirHistorico();
            return "";
        }

        // Obter informações da conversão
        ConversaoInfo info = obterInfoConversao(opcao);
        if (info == null) {
            System.out.println("Opção inválida!");
            return "";
        }

        // Realizar a conversão
        double taxa = obterTaxaEntreMoedas(info.moedaOrigem, info.moedaDestino);
        if (taxa == 0) {
            String mensagemErro = String.format("Não foi possível obter a taxa de câmbio entre %s e %s.%n",
                    info.moedaOrigem, info.moedaDestino);
            System.out.println(mensagemErro);
            return mensagemErro;
        }

        double resultado = valor * taxa;
        String registro = formatarResultado(valor, info.moedaOrigem, resultado, info.moedaDestino);

        // Atualizar histórico e retornar resultado
        historico.add(registro);
        System.out.println(registro);
        return registro;
    }

    // Classe auxiliar para armazenar informações da conversão
    private static class ConversaoInfo {
        String moedaOrigem;
        String moedaDestino;

        public ConversaoInfo(String origem, String destino) {
            this.moedaOrigem = origem;
            this.moedaDestino = destino;
        }
    }

    // Método para obter informações da conversão
    private ConversaoInfo obterInfoConversao(int opcao) {
        switch (opcao) {
            case 1:
                return new ConversaoInfo("USD", "ARS");
            case 2:
                return new ConversaoInfo("ARS", "USD");
            case 3:
                return new ConversaoInfo("USD", "BRL");
            case 4:
                return new ConversaoInfo("BRL", "USD");
            case 5:
                return new ConversaoInfo("USD", "COP");
            case 6:
                return new ConversaoInfo("COP", "USD");
            case 7:
                return new ConversaoInfo("GBP", "USD");
            case 8:
                return new ConversaoInfo("USD", "GBP");
            default:
                return null;
        }
    }

    // Método para formatar o resultado
    private String formatarResultado(double valor, String origem, double resultado, String destino) {
        return String.format("%.2f %s → %.2f %s", valor, origem, resultado, destino);
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma conversão realizada ainda.");
        } else {
            for (String conversao : historico) {
                System.out.println(conversao);
            }
        }
    }

}
