import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        double[][] matriz_teste = new double[][] {
            {2,1,4},
            {0,2,1},
            {3,0,5}
        };

        double[][] matriz_teste2 = new double[][] {
            {0}
        };

        Matriz m = new Matriz(matriz_teste);

        System.out.println(m.determinante);

        if(m.determinante == 0) {
            scanner.close();
            throw new RuntimeException("Determinante da matriz é zero!");
        }

        m.inversa();


        
        /* 

        System.out.println("det(A) = " + det(matriz_teste));

        if(det(matriz_teste) != 0) {

        }

        System.out.println("Digite o tamanho da matriz: \n>>");
        int tam_mat = scanner.nextInt();  // User input

        matriz = new double[tam_mat][tam_mat]; // Alocando espaço

        for(int i = 0; i < tam_mat; i++) {
            for(int j = 0; j < tam_mat; j++) {
                System.out.println("Valor de [" + (i + 1) + "][" +(j + 1) + "] \n>>");
                matriz[i][j] = scanner.nextDouble();
            }
        }

        for(int i = 0; i < tam_mat; i++) {
            for(int j = 0; j < tam_mat; j++) {
                System.out.println("Valor de [" + (i + 1) + "][" +(j + 1) + "]\n");
                System.out.println(matriz[i][j]);
            }
        }

        System.out.println("det(A) = " + det(matriz));
        */

        scanner.close();
    }
}
