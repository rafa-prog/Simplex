import utils.Aux;

public class Matriz {
	double determinante;
	double[][] inversa;
    double[][] identidade;
	DecompLU dLU;

    public Matriz (double[][] matriz) {
        determinante = det(matriz);

		Aux.print("A:", matriz);

		identidade = new double[matriz.length][matriz.length];

		for (int i = 0; i < matriz.length; i++) {
			identidade[i][i] = 1;
		}
    }

	
    public double det (double[][] matriz) {
		double[][] mat_temp;
		double resultado = 0;

		if (matriz.length == 1) {
			resultado = matriz[0][0];
			return resultado;
		}

		if (matriz.length == 2) {
			return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
		}
		

		for (int i = 0; i < matriz.length; i++) {
			mat_temp = new double[matriz.length - 1][matriz.length - 1];
			for (int j = 1; j < matriz.length; j++) {
				for (int k = 0; k < matriz.length; k++) {
					if (k < i) {
						mat_temp[j - 1][k] = matriz[j][k];
					}else if (k > i) {
						mat_temp[j - 1][k - 1] = matriz[j][k];
					}
				}
			}

			resultado += matriz[0][i] * Math.pow (-1, i) * det(mat_temp);
		}

		System.out.println("det = " + resultado + "\n\n");
		return resultado;
	}

    public void inverteMatriz(double[][] matriz) {
		// Tratamento para matriz singular
		if (determinante == 0) {
            throw new ArithmeticException("Matriz singular!");
        }

		// Tratamento para elemento da diagonal principal nulo
		//matriz = Aux.verifPivo(matriz, 0);

		inversa = new double[matriz.length][matriz.length];
		dLU = new DecompLU(matriz);

		// Resolve LUx = b
		for (int i = 0; i < identidade.length; i++) {
			double[] b = new double[identidade.length];

			for (int j = 0; j < identidade.length; j++) {
				b[j] = identidade[j][i];
			}

			double[] aux = dLU.resolveSistema(b);

			for (int j = 0; j < identidade.length; j++) {
				inversa[j][i] = aux[j];
			}
		}

		Aux.print("A⁻¹:", inversa);
    }
}
