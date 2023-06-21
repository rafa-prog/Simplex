import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Matriz matriz_teste_zero = new Matriz(new double[][] {
            {0}
        });

        Matriz matriz_teste1 = new Matriz(new double[][] {
            {1,2,3},
            {0,1,0},
            {1,0,2}
        });

        Matriz matriz_teste2 = new Matriz(new double[][] {
            {-2,4,3},
            {0,1,0},
            {1,-2,-1}
        });

        matriz_teste1.inversa();

        System.out.println(matriz_teste1.determinante);
        System.out.println(matriz_teste2.determinante);
        

        //Matriz mZero = new Matriz(matriz_teste_zero);

        Matriz mat_result = new Matriz(matriz_teste1.multiplicaMat(matriz_teste2.matriz));
        System.out.println(mat_result.determinante);

        System.out.println();
        mat_result.print();


        

        /*

        System.out.println("Digite o tamanho da matriz: \n>>");
        int tam_mat = scanner.nextInt();  // User input

        matriz = new double[tam_mat][tam_mat]; // Alocando espaço

        for(int i = 0; i < tam_mat; i++) {
            for(int j = 0; j < tam_mat; j++) {
                System.out.println("Valor de [" + (i + 1) + "][" +(j + 1) + "] \n>>");
                matriz[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("det(A) = " + det(matriz));
        */

        scanner.close();
    }
}
