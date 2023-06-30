import utils.Aux;

public class Matriz {
	double determinante;
	double[][] inversa;
    double[][] identidade;
	DecompLU dLU;

    public Matriz (double[][] matriz) {
        determinante = det(matriz);

		print("A:", matriz);

		identidade = new double[matriz.length][matriz.length];

		for (int i = 0; i < matriz.length; i++) {
			identidade[i][i] = 1;
		}
    }

	public void print (String txt, double[][] matriz) {

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

			resultado += matriz[0][i] * Math.pow (-1, (i + 1)) * det(mat_temp);
		}

		System.out.println("det = " + resultado + "\n\n");
		return resultado;
	}

	public void trocaLinhasMatriz(double[][] matriz, int linha, int it) {
		if (it > Aux.fat(matriz.length) || it <= 0) {
			throw new RuntimeException("Matriz inválida");
		}

		int troca = 0;

		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i][linha] != 0 && matriz[linha][i] != 0) {
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
		for (int i = 0; i < identidade.length; i++) {
			if (matriz[i][i] == 0) {
				trocaLinhasMatriz(matriz, i, it++);
			}
		}
	}

    public void inverteMatriz(double[][] matriz) {
		// Tratamento para matriz singular
		if (determinante == 0) {
            throw new ArithmeticException("Matriz singular!");
        }

		// Tratamento para elemento da diagonal principal nulo
		for (int i = 0; i < identidade.length; i++) {
			if (matriz[i][i] == 0) {
				trocaLinhasMatriz(matriz, i, 1);
			}
		}

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

		print("A⁻¹:", inversa);
    }
}
