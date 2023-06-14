import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Double[][] matriz;

        Double[][] matriz_teste = new Double[][] {
            {2.,1.,4.},
            {0.,2.,1.},
            {3.,0.,5.}
        };

        System.out.println("det(A) = " + det(matriz_teste));

        System.out.println("Digite o tamanho da matriz: \n>>");
        int tam_mat = scanner.nextInt();  // User input

        matriz = new Double[tam_mat][tam_mat]; // Alocando espaço

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

        scanner.close();
    }

    public static double det (Double[][] matrix) {
		Double[][] temporary;
		double result = 0;

		if (matrix.length == 1) {
			result = matrix[0][0];
			return (result);
		}

		for (int i = 0; i < matrix[0].length; i++) {
			temporary = new Double[matrix.length - 1][matrix[0].length - 1];

			for (int j = 1; j < matrix.length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					if (k < i) {
						temporary[j - 1][k] = matrix[j][k];
					} else if (k > i) {
						temporary[j - 1][k - 1] = matrix[j][k];
					}
				}
			}

			result += matrix[0][i] * Math.pow (-1, (double) i) * det(temporary);
		}
		return (result);
	}

}
