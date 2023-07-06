import utils.Aux;

public class Matriz {
	
    public double det (double[][] matriz) {
		double[][] mat_temp;
		double determinante = 0;

		if (matriz.length == 1) {
			determinante = matriz[0][0];
			return determinante;
		}

		if (matriz.length == 2) {
			return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
		}
		

		for (int i = 0; i < matriz.length; i++) {
			mat_temp = new double[matriz.length - 1][matriz[0].length - 1];
			for (int j = 1; j < matriz[0].length; j++) {
				for (int k = 0; k < matriz.length; k++) {
					if (k < i) {
						mat_temp[j - 1][k] = matriz[j][k];
					}else if (k > i) {
						mat_temp[j - 1][k - 1] = matriz[j][k];
					}
				}
			}

			determinante += matriz[0][i] * Math.pow (-1, i) * det(mat_temp);
		}

		return determinante;
	}

	public double[][] identidade (double[][] matriz) {
		double[][] identidade = new double[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			identidade[i][i] = 1;
		}

		return identidade;
	}

	public double[][] transposta(double[][] matriz) {
		double[][] transposta = new double[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				transposta[j][i] = matriz[i][j];
			}
		}
		return transposta;
	}

    public double[][] inversa(double[][] matriz) {
		// Tratamento para matriz singular
		if (det(matriz) == 0) {
            throw new ArithmeticException("Matriz singular!");
        }

		double[][] inversa = new double[matriz.length][matriz[0].length];
		double[][] identidade = identidade(matriz);

		DecompLU dLU = new DecompLU(matriz);

		// Resolve LUx = b
		for (int i = 0; i < identidade.length; i++) {
			double[] b = new double[identidade[0].length];
			
			for (int j = 0; j < identidade[0].length; j++) {
				b[j] = identidade[j][i];
			}

			double[] aux = dLU.resolveSistema(b);

			for (int j = 0; j < identidade[0].length; j++) {
				inversa[j][i] = aux[j];
			}
		}

		return inversa;
    }
}
