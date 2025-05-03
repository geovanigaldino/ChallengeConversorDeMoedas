import java.util.Scanner;

public class Main {
    private static final int SAIR = 0; // Opção para encerrar o programa
    private static final int TOTAL_OPCOES = 9; // Número de opções disponíveis no menu

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConversorDeMoedas conversor = new ConversorDeMoedas();

        try {
            while (true) {
                exibirMenu(); // Exibe o menu com as opções
                int opcao = lerOpcao(leitura); // Lê e valida a opção escolhida

                if (opcao == SAIR) { // Encerra o programa se escolher 0
                    System.out.println("Programa encerrado. Até a próxima!");
                    break;
                }

                if (opcao != TOTAL_OPCOES) {
                    double valor = lerValor(leitura);
                    String resultado = conversor.converter(opcao, valor); // Agora retorna o resultado
                    System.out.println("\nResultado da conversão: " + resultado); // Exibe o resultado
                } else {
                    System.out.println("\nHistórico de conversões:");
                    conversor.exibirHistorico(); // Exibe todas as conversões anteriores
                }
            }
        } finally {
            leitura.close(); // Garante que o Scanner seja fechado
        }
    }

    private static void exibirMenu() {
        System.out.println("*".repeat(50));
        System.out.println("Seja bem-vindo ao Conversor de Moedas\n");
        System.out.println("1 - Dólar → Peso Argentino");
        System.out.println("2 - Peso Argentino → Dólar");
        System.out.println("3 - Dólar → Real Brasileiro");
        System.out.println("4 - Real Brasileiro → Dólar");
        System.out.println("5 - Dólar → Peso Colombiano");
        System.out.println("6 - Peso Colombiano → Dólar");
        System.out.println("7 - Libra → Dólar");
        System.out.println("8 - Dólar → Libra");
        System.out.println("9 - Ver histórico de conversões"); // Corrigido o acento (conversês → conversões)
        System.out.println("0 - Sair");
        System.out.print("\nEscolha uma opção válida: ");
    }

    private static int lerOpcao(Scanner scanner) {
        // Lê e valida a opção do menu escolhida pelo usuário
        while (true) {
            try {
                String input = scanner.nextLine();
                int opcao = Integer.parseInt(input);

                if (opcao >= SAIR && opcao <= TOTAL_OPCOES) { // Usa a constante SAIR em vez de 0
                    return opcao;
                } else {
                    System.out.print("Opção inválida. Digite um número entre " + SAIR + " e " + TOTAL_OPCOES + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número inteiro: ");
            }
        }
    }

    private static double lerValor(Scanner scanner) {
        // Lê e valida o valor a ser convertido, aceitando ',' ou '.' como separador decimal
        while (true) {
            System.out.print("Digite o valor a ser convertido (deve ser positivo): ");
            try {
                String input = scanner.nextLine();
                double valor = Double.parseDouble(input.replace(",", "."));

                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("Valor inválido. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Use números com . ou , como separador decimal.");
            }
        }
    }
}