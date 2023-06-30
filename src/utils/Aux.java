package utils;

public class Aux {
    public boolean compararMatrizes(double[][] matriz1, double[][] matriz2) {
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

	public double[][] multiplicaMatrizes (double[][] matriz1, double[][] matriz2) {
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

    public static int fat (int num) {
		int result = num;
        if (num > 1) {
            return result * fat(num - 1);
        }
		return result;
    }
}
