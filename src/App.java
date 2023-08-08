import java.util.Scanner;

import utils.Aux;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        /*
        // Testes manuais
        double[][] m1 = new double[][] {
            {1,2,3},
            {0,1,0},
            {1,0,2}
        };

        double[][] m2 = new double[][] {
            {2,2,3},
            {4,5,6},
            {7,8,9}
        };

        double[][] m3 = new double[][] {
            {0,2,4},
            {0,1,0},
            {1,0,4}
        };

        double[][] m4 = new double[][] {
            {2,2,3},
            {4,4,2},
            {0,5,5}
        };

        double[][] m6 = new double[][] {
            {1,1,4},
            {1,0,3},
            {0,1,3.5}
        };
        //*/
        //*
        //*
        Matriz matriz = new Matriz(); // Funções de matriz

        int[] simbolos;
        double[] fx;
        double[][] A;

        System.out.print("Digite o tamanho da matriz: \n>> ");
        int tam = Integer.parseInt(scanner.nextLine());  // User input
        System.out.println();

        A = new double[tam][tam]; // Alocando espaço
        simbolos = new int[tam];
        fx = new double[tam];

        boolean max = false; // Definindo max e min

        /* 
        System.out.print("(min/max) \n>> ");
        String tipoFuncao = scanner.nextLine();
        System.out.println();
        
        if (tipoFuncao == "max") {
            max = true;
        }
        */
        //*
        for (int i = 0; i < A.length; i++) {
            System.out.print("Valor de x" + (i + 1) + "\n>> ");
            fx[i] = Double.parseDouble(scanner.nextLine());
        }

        System.out.println();

        for(int i = 0; i < tam; i++) {
            for(int j = 0; j < tam - 1; j++) {
                System.out.print("Valor de a" + (i + 1) + (j + 1) + "\n>> ");
                A[i][j] = Double.parseDouble(scanner.nextLine());
            }

            System.out.print("\nSinal do sistema \n>> ");

            char aux = scanner.nextLine().charAt(0);
            if (aux == '<') {
                simbolos[i]++;
            }else if (aux == '>') {
                simbolos[i]--;
            }

            System.out.print("\nValor de a" + (i + 1) + tam + "\n>> ");
            A[i][tam - 1] = Double.parseDouble(scanner.nextLine());
        }

        System.out.println();

        System.out.println("det(A) = " + matriz.det(A) + "\n\n");
        Aux.print("A:", A);

        Simplex s = new Simplex(matriz, max, fx, A, simbolos);
        // */
        scanner.close();
    }
}
