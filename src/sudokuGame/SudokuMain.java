/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author caiqu
 */
public class SudokuMain {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        game();
    }

    public static void game() {
        char matriz[][];
        matriz = initialize();

        // variavel para receber o status do game
        int loop = -1;

        System.out.println("As regras do sudoku (que em japonês significa \"número único) são simples e, apesar de");
        System.out.println("apresentar números, não é necessário fazer qualquer tipo de conta. Basta completar todos");
        System.out.println("os espaços seguindo as seguintes restrições: Não repetir números na mesma linha, na");
        System.out.println("mesma coluna nem na mesma grade 3x3.");
        System.out.println("");
        System.out.println("BEM VINDO AO SUDOKU");
        System.out.println("Esse será o seu desafio");
        System.out.println("");
        print(matriz);

        while (loop == -1) {
            System.out.println("");
            System.out.print("Digite 1 para inserir na matriz e 2 para limpar alguma posição da matriz: ");
            int opcao = sc.nextInt();

            while (opcao != 1 || opcao != 2) {
                if (opcao == 1 || opcao == 2) {
                    break;
                }
                System.out.print("Digite um número válido: ");
                opcao = sc.nextInt();
            }

            System.out.println("");
            System.out.print("Digite o número da linha: ");
            int linha = sc.nextInt();
            if (linha > 8 || linha < 0) {
                System.out.print("Digite um número que tenha no indice: ");
                linha = sc.nextInt();
            }
            System.out.print("Digite o número da coluna: ");
            int coluna = sc.nextInt();
            if (coluna > 8 || coluna < 0) {
                System.out.print("Digite um número que tenha no indice: ");
                coluna = sc.nextInt();
            }

            if (opcao == 1) {
                System.out.print("Digite o número que deseja inserir: ");
                String numero = sc.next();
                step(matriz, linha, coluna, numero.charAt(0), false);

            } else if (opcao == 2) {
                step(matriz, linha, coluna, '0', true);
//                limpar(matriz, linha, coluna);

            }
            System.out.println("");
            print(matriz);

            if (status(matriz) == true) {
                System.out.println("");
                System.out.println("PARABÉNS VOCÊ COMPLETOU O SUDOKU");
                break;
            }
        }
    }

    public static char[][] initialize() {
        String path = ("tabuleiro.txt");
        FileReader fr = null;
        BufferedReader br = null;
        char matriz[][] = new char[9][9];
        String array[];
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            while (true) {
                String linha = br.readLine();
                if (linha == null) {
                    break; // quebra o laco
                }
                array = linha.split(" ");

                for (int i = 0; i < matriz.length; i++) {               //aqui basicamente está sendo inserido os caracteres
                    for (int j = 0; j <= matriz[0].length - 1; j++) {   //que foi pego pelo split
                        matriz[i][j] = array[j].charAt(0);              //onde o array é a linha do documento
                        //então o caracter da posição 0 é adicionado na posição [i][j] da matriz 
                    }

                    linha = br.readLine();
                    if (linha != null) {                // aqui ele prosegue até acabar as linhas 
                        array = linha.split(" ");
                    } else {
                        break;
                    }

                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return matriz;

    }

    public static void print(char matriz[][]) {
        System.out.printf("   0   1   2   3   4   5   6   7   8");
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) {            //função responsavel pela exibição da matriz               
            System.out.printf("%d ", i);
            for (int j = 0; j < matriz[i].length; j++) {

                System.out.printf("[%c] ", matriz[i][j]);

            }
            System.out.printf("%d ", i);
            System.out.println("");
        }
    }

    public static int step(char matriz[][], int linha, int coluna, char jogada, boolean limpar) {
        String status = null;
        int verificar = -1; //variavel que irei utilizar para verificar se a posição é valida 
        if (limpar == true) {
            for (int i = 0; i < matriz.length; i++) {           // aqui eu percorro a matriz e insiro o '_' nas cordenadas que o 
                for (int j = 0; j < matriz[0].length; j++) {    // usuário passou
                    matriz[linha][coluna] = '_';
                }
            }
        } else {

            for (int i = 0; i < matriz.length; i++) {
                if (matriz[linha][i] == jogada) { // verifica se o número que o usuário digitou está sendo utilizado na linha
                    verificar = 0; //se estiver sendo utilizado, verificar vai receber o resultado
                    status = "LINHA";
                }
            }

            for (int i = 0; i < matriz.length; i++) {
                if (matriz[i][coluna] == jogada) { // verifica se o número que o usuário digitou está sendo utilizado na coluna
                    verificar = 0; //se estiver sendo utilizado, verificar vai receber o resultado

                    status = "COLUNA";
                }
            }

            int quadranteLinha = (linha / 3) * 3;                 
            int quadranteColuna = (coluna / 3) * 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (matriz[quadranteLinha + i][quadranteColuna + j] == jogada) {
                        verificar = 0;
                        status = "Matriz 3x3";
                    }
                }
            }

            if (verificar != 0) { // Basicamente esse if é reponsavel por verificar se é possivel inserir na matriz
                for (int k = 0; k < matriz.length; k++) {  //ele recebe a variavel que foi passado no teste acima
                    for (int l = 0; l < matriz[0].length; l++) {
                        if (matriz[linha][coluna] == '_') {   //aqui ele verifica se a posição está vazia e insere na tabela
                            matriz[linha][coluna] = jogada;
                            verificar = 1;
                        }
                    }
                }
            } else {
                System.out.println("NÚMERO JÁ EXISTE NA " + status); // caso o número não possa ser inserido eu utilizo a variavel status
                // onde eu trago a informação da onde o número está reptindo
            }

        }

        return verificar;
    }

    public static boolean status(char matriz[][]) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == '_') { //condição que verifica se ainda tem algum campo vazio
                    return false;
                }
            }
        }
        return true;

    }
}
