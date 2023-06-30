import java.util.Scanner;

import utils.Aux;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        /*
        
        // Testes manuais
        double[][] matriz_teste1 = new double[][] {
            {1,2,3},
            {0,1,0},
            {1,0,2}
        };

        double[][] matriz_teste2 = new double[][] {
            {2,2,3},
            {4,5,6},
            {7,8,9}
        };

        double[][] matriz_teste3 = new double[][] {
            {0,2,4},
            {0,1,0},
            {1,0,4}
        };

        double[][] matriz_teste4 = new double[][] {
            {2,2,3},
            {4,4,2},
            {0,5,5}
        };

        Matriz m = new Matriz(matriz_teste4);

        m.inverteMatriz(matriz_teste4);
        */

        double[][] matriz_teste4 = new double[][] {
            {2,2,3},
            {4,4,2},
            {0,5,5}
        };

        Matriz m = new Matriz(matriz_teste4);

        m.inverteMatriz(matriz_teste4);

        m.det(m.inversa);

        double[][] teste = Aux.multiplicaMatrizes(m.dLU.l, m.dLU.u);

        Aux.print("L * U:", teste);


        System.out.println("Digite o tamanho da matriz: \n>>");
        int tam = scanner.nextInt();  // User input

        double[][] matriz = new double[tam][tam]; // Alocando espaço

        for(int i = 0; i < tam; i++) {
            for(int j = 0; j < tam; j++) {
                System.out.println("Valor de a" + (i + 1) + (j + 1));
                System.out.print(">> ");
                matriz[i][j] = scanner.nextDouble();
            }
        }

        System.out.println();

        // Inicializa A e I, imprime, e calcula det(A)
        Matriz A = new Matriz(matriz);

        System.out.println("det(A) = " + A.determinante + "\n\n");
        A.inverteMatriz(matriz);

        Aux.print("A:",matriz);

        A.det(matriz);

        // */

        scanner.close();
    }

    
}
