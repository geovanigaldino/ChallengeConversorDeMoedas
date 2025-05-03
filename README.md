# 💱 Conversor de Moedas com API ExchangeRate 💰

Este projeto é um **Conversor de Moedas** desenvolvido em Java, utilizando a [API ExchangeRate](https://www.exchangerate-api.com/) para buscar taxas de câmbio em tempo real. O projeto foi desenvolvido como parte do curso 'Praticando Java: Challenge conversor de moedas' desafio proposto pelo curso.

## 🚀 Funcionalidades

- Consulta de taxas de câmbio em tempo real.
- Conversão entre diversas moedas:
  - Dólar (USD)
  - Real (BRL)
  - Peso Argentino (ARS)
  - Peso Colombiano (COP)
  - Libra Esterlina (GBP)
- Tratamento de erros na requisição da API.
- Validação de moedas inexistentes ou inválidas.
- Menu interativo via console.
- 💡 **Desafio extra implementado:** Histórico de conversões realizadas.

## 📌 Desafio Extra

Como desafio adicional, foi implementado um sistema de **Histórico de Conversões**, que registra todas as conversões feitas durante a execução do programa. Os dados são armazenados em memória e podem ser visualizados a qualquer momento através do menu.

## 🛠️ Tecnologias e ferramentas

- Java 17+
- IntelliJ IDEA
- Biblioteca [Gson](https://github.com/google/gson) para desserialização de JSON
- Java HttpClient (`java.net.http`) para comunicação com a API

## ⚙️ Como Executar

1. Certifique-se de ter o Java JDK instalado (versão 11 ou superior)
2. Clone o repositório: 
   ```bash
   git clone https://github.com/geovanigaldino/ChallengeConversorDeMoedas.git
   cd conversor-moedas
3. Navegue até o diretório do projeto:
   ```bash
   cd ChallengeConversorDeMoedas
4. Compile e execute:
   ```bash
   javac Main.java main.java.com.conversor.service.ConversorDeMoedas.java
   java Main
