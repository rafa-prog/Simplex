package utils;

public class Aux {
	public static void print (String txt, double[][] matriz) {

		System.out.println(txt);

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


	public static int fat (int num) {
		int result = num;
        if (num > 1) {
            return result * fat(num - 1);
        }
		return result;
    }


    public static boolean compararMatrizes(double[][] matriz1, double[][] matriz2) {
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


	public static double[][] multiplicaMatrizes (double[][] matriz1, double[][] matriz2) {
		if (matriz1.length != matriz2.length) {
			throw new ArithmeticException("Tamanho diferente entre matrizes!");
		}

		double[][] mat_result = new double[matriz1.length][matriz1.length];
		
		for (int i = 0; i < matriz1.length; i++) {
			for (int k = 0; k < matriz1.length; k++) {
				for (int j = 0; j < matriz1.length; j++) {
					mat_result[i][j] += matriz1[i][k] * matriz2[k][j];
				}
			}
		}

		return mat_result;
	}


	public static double[][] verifPivo (double[][] matriz, int it) {
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i][i] == 0) {
				matriz = trocaLinhasMatriz(matriz, i, it++);
			}
		}

		return matriz;
	}


	public static double[][] trocaLinhasMatriz(double[][] matriz, int linha, int it) {
		if (it > Aux.fat(matriz.length) || it <= 0) {
			throw new RuntimeException("Matriz inválida");
		}

		int troca = 0;

		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i][linha] != 0 && matriz[linha][i] != 0 && i != linha) {
				if (linha > 0 && matriz[i][linha] == matriz[i][linha - 1]) {
					continue;
				}
				double aux;
				troca = i;
				for (int j = 0; j < matriz.length; j++) {
					aux = matriz[i][j];
					matriz[i][j] = matriz[linha][j];
					matriz[linha][j] = aux;
				}
			}
		}

		print("L" + (linha + 1) + " <-> L" + (troca + 1), matriz);

		// Confere se precisa de mais trocas
		verifPivo(matriz, it);

		return matriz;
	}
}
