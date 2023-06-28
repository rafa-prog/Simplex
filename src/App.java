import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // /*
        Matriz matriz_teste1 = new Matriz(new double[][] {
            {1,2,3},
            {0,1,0},
            {1,0,2}
        });
        // */
        Matriz matriz_teste2 = new Matriz(new double[][] {
            {2,2,3},
            {4,5,6},
            {7,8,9}
        });

        matriz_teste1.inverteMat();

        print(matriz_teste1.inversa);

        // /*

        System.out.println("Digite o tamanho da matriz: \n>>");
        int tam = scanner.nextInt();  // User input

        double[][] matriz = new double[tam][tam]; // Alocando espaço

        for(int i = 0; i < tam; i++) {
            for(int j = 0; j < tam; j++) {
                System.out.println("Valor de [" + (i + 1) + "][" +(j + 1) + "] \n>>");
                matriz[i][j] = scanner.nextDouble();
            }
        }

        Matriz A = new Matriz(matriz);

        System.out.println("det(A) = " + A.determinante);
        A.inverteMat();
        // */

        scanner.close();
    }

    public static void print(double[][] matriz) {
		for(double[] linha : matriz) {

			System.out.print("[ ");

			for(double valor : linha) {
				if (valor >= 0) {
					System.out.print(" ");
				}
				
				System.out.print(valor + " ");
			}

			System.out.println("] ");
		}

		System.out.println();
	}

    private static boolean comparaMat(double[][] matriz1, double[][] matriz2) {
		if (matriz1.length != matriz2.length) {
			throw new ArithmeticException("Tamanho diferente entre matrizes!");
		}

		for (int i = 0; i < matriz1.length; i++) {
			for (int j = 0; j < matriz1[i].length; j++) {
				if (matriz1[i][j] != matriz2[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}
