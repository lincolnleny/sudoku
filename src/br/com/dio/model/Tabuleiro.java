package br.com.dio.model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private Celula[][] grade;

    public Tabuleiro() {
        grade = new Celula[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grade[i][j] = new Celula();
            }
        }
    }

    public void setCelula(int linha, int coluna, Celula celula) {
        grade[linha][coluna] = celula;
    }

    public boolean inserirValor(int linha, int coluna, int valor) {
        if (linha < 0 || linha > 8 || coluna < 0 || coluna > 8 || valor < 1 || valor > 9) {
            return false;
        }
        Celula celula = grade[linha][coluna];
        if (celula.isFixa()) return false;

        if (!isValido(linha, coluna, valor)) return false;

        celula.setValor(valor);
        return true;
    }

    public boolean isValido(int linha, int coluna, int valor) {
        for (int c = 0; c < 9; c++) {
            if (grade[linha][c].getValor() == valor) return false;
        }
        for (int l = 0; l < 9; l++) {
            if (grade[l][coluna].getValor() == valor) return false;
        }
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;
        for (int l = inicioLinha; l < inicioLinha + 3; l++) {
            for (int c = inicioColuna; c < inicioColuna + 3; c++) {
                if (grade[l][c].getValor() == valor) return false;
            }
        }
        return true;
    }

    public List<Integer> getValoresValidos(int linha, int coluna) {
        List<Integer> validos = new ArrayList<>();
        if (linha < 0 || linha > 8 || coluna < 0 || coluna > 8) return validos;
        if (grade[linha][coluna].isFixa()) return validos;

        for (int valor = 1; valor <= 9; valor++) {
            if (isValido(linha, coluna, valor)) {
                validos.add(valor);
            }
        }
        return validos;
    }

    public boolean estaCompleto() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grade[i][j].getValor() == 0) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-------+-------+-------+\n");
        for (int i = 0; i < 9; i++) {
            sb.append("| ");
            for (int j = 0; j < 9; j++) {
                sb.append(grade[i][j].toString()).append(" ");
                if ((j + 1) % 3 == 0) sb.append("| ");
            }
            sb.append("\n");
            if ((i + 1) % 3 == 0) sb.append("+-------+-------+-------+\n");
        }
        return sb.toString();
    }
}
