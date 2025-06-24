package br.com.dio.model;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();

       
        if (args.length == 0) {
            String[][] puzzles = gerarPuzzlesFixos();
            String[] selecionado = puzzles[new Random().nextInt(puzzles.length)];

            for (String arg : selecionado) {
                String[] partes = arg.split(";");
                String[] pos = partes[0].split(",");
                int linha = Integer.parseInt(pos[0]);
                int coluna = Integer.parseInt(pos[1]);
                int valor = Integer.parseInt(partes[1]);
                boolean fixa = Boolean.parseBoolean(partes[2]);
                tabuleiro.setCelula(linha, coluna, new Celula(valor, fixa));
            }
        } else {
            for (String arg : args) {
                String[] partes = arg.split(";");
                String[] pos = partes[0].split(",");
                int linha = Integer.parseInt(pos[0]);
                int coluna = Integer.parseInt(pos[1]);
                int valor = Integer.parseInt(partes[1]);
                boolean fixa = Boolean.parseBoolean(partes[2]);
                tabuleiro.setCelula(linha, coluna, new Celula(valor, fixa));
            }
        }

        Scanner scanner = new Scanner(System.in);

        while (!tabuleiro.estaCompleto()) {
            System.out.println("\nTabuleiro atual:");
            System.out.println(tabuleiro);
            System.out.print("Digite linha coluna valor (ex: 0 1 5): ");

            try {
                int linha = scanner.nextInt();
                int coluna = scanner.nextInt();

                List<Integer> validos = tabuleiro.getValoresValidos(linha, coluna);
                if (validos.isEmpty()) {
                    System.out.println("Posição inválida ou célula fixa. Tente outra.");
                    scanner.nextLine(); // limpar buffer
                    continue;
                }
                System.out.println("Valores válidos para essa posição: " + validos);

                int valor = scanner.nextInt();

                if (!validos.contains(valor)) {
                    System.out.println("Valor inválido para esta posição. Tente novamente.");
                    scanner.nextLine();
                    continue;
                }

                boolean inserido = tabuleiro.inserirValor(linha, coluna, valor);
                if (!inserido) {
                    System.out.println("Jogada inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("\nEntrada inválida. Use três números inteiros.");
                scanner.nextLine(); // limpar buffer
            }
        }

        System.out.println("\n🎉 Parabéns! Você completou o Sudoku!");
        System.out.println(tabuleiro);
        scanner.close();
    }

    private static String[][] gerarPuzzlesFixos() {
        return new String[][]{
            new String[]{
                "0,0;5;true", "1,0;3;true", "4,0;7;true",
                "0,1;6;true", "3,1;1;true", "4,1;9;true", "5,1;5;true",
                "1,2;9;true", "2,2;8;true", "7,2;6;true",
                "0,3;8;true", "4,3;6;true", "8,3;3;true",
                "0,4;4;true", "3,4;8;true", "5,4;3;true", "8,4;1;true",
                "0,5;7;true", "4,5;2;true", "8,5;6;true",
                "1,6;6;true", "6,6;2;true", "7,6;8;true",
                "3,7;4;true", "4,7;1;true", "5,7;9;true", "8,7;5;true",
                "4,8;8;true", "7,8;7;true", "8,8;9;true"
            },
            new String[]{
                "0,1;2;true", "0,3;6;true", "0,5;8;true", "0,7;3;true",
                "1,0;3;true", "1,1;6;true", "1,4;4;true", "1,7;5;true",
                "2,2;1;true", "2,6;2;true",
                "3,1;5;true", "3,6;1;true",
                "4,0;6;true", "4,8;4;true",
                "5,2;3;true", "5,7;6;true",
                "6,2;7;true", "6,6;8;true",
                "7,1;9;true", "7,4;2;true", "7,7;7;true", "7,8;6;true",
                "8,1;1;true", "8,3;5;true", "8,5;9;true", "8,7;4;true"
            }
        };
    }
}
