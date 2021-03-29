/*
Entrega de trabalho
NOME: Caique Moreira Leite Solda, CURSO: análise e desenvolvimento de sistemas
declaramos que
todas as respostas são fruto de nosso próprio trabalho,
não copiamos respostas de colegas externos à equipe,
não disponibilizamos nossas respostas para colegas externos à equipe e
não realizamos quaisquer outras atividades desonestas para nos beneficiar ou prejudicar 
outros.
 */
package sudokuGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Caique Moreira Leite Solda
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

            while (opcao != 1 || opcao != 2) { // aqui estou colocando que se o número o numero for diferente de 1 ou 2 ele repete
                if (opcao == 1 || opcao == 2) {  //quando for igual a 1 ou 2 ele para o laço e deita a pessoa adicionar o número
                    break;
                }
                System.out.print("Digite um número válido: ");
                opcao = sc.nextInt();
            }

            System.out.println("");
            System.out.print("Digite o número da linha: ");
            int linha = sc.nextInt();
            while (linha > 8 || linha < 0) { // aqui eu valido se o número digitado tem no indice da matriz 
                System.out.print("Digite um número que tenha no indice: "); //caso o número não tenha ele repete até o usuário digitar um válido
                linha = sc.nextInt();
            }
            System.out.print("Digite o número da coluna: ");
            int coluna = sc.nextInt();
            while (coluna > 8 || coluna < 0) { // aqui eu valido se o número digitado tem no indice da matriz 
                System.out.print("Digite um número que tenha no indice: ");//caso o número não tenha ele repete até o usuário digitar um válido
                coluna = sc.nextInt();
            }

            if (opcao == 1) { //aqui eu verifico a opção do usuário 
                System.out.print("Digite o número que deseja inserir: ");
                String numero = sc.next();
                step(matriz, linha, coluna, numero.charAt(0), false); //caso seja igual 1, ele adiciona 

            } else if (opcao == 2) { //aqui eu verifico a opção do usuário 
                step(matriz, linha, coluna, '0', true); //caso seja igual 2, ele limpa no indice que ele deseja

            }
            System.out.println("");
            print(matriz);

            if (status(matriz) == true) { // aqui eu verifico se ainda tem espaço em branco e se não tiver a função retorna true 
                System.out.println(""); // e o jogo finaliza
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

            int quadranteLinha = (linha / 3) * 3;  // utilizei parte da logíca mostrada em aula onde limitava o tamanho da matriz  
            int quadranteColuna = (coluna / 3) * 3;
            for (int i = 0; i < 3; i++) {  //Criei os for para percorrer linha e coluna da matriz e os limitei do tamanho que vai ser necessario caminhar que no caso seria 3x3
                for (int j = 0; j < 3; j++) {
                    if (matriz[quadranteLinha + i][quadranteColuna + j] == jogada) { //aqui ele verificar se o número digitado tem no quadrante 
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
            for (int j = 0; j < matriz[0].length; j++) { //função responsavel por percorrer a matriz e verificar
                if (matriz[i][j] == '_') { //ainda tem algum campo vazio 
                    return false;
                }
            }
        }
        return true;

    }
}
