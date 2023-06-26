public class Matriz {
	double determinante;
    double[][] matriz;
	double[][] inversa;
    double[][] identidade;
	double[][] l, u;

    public Matriz(double[][] matriz) {
        this.matriz = matriz;
        determinante = det(matriz);

		identidade = new double[matriz.length][matriz.length];

		for (int i = 0; i < matriz.length; i++) {
			identidade[i][i] = 1;
		}

		inversa = null;
    }


	public void print(double[][] test) {
		for(double[] linha : test) {

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

	private boolean equals(double[][] matriz1, double[][] matriz2) {
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
	
    private double det(double[][] matriz) {
		double[][] mat_temp;
		double resultado = 0;

		if (matriz.length == 1) {
			resultado = matriz[0][0];
			return resultado;
		}

		if (matriz.length == 2)
			return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];

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
		return resultado;
	}

	public double[][] multiplicaMat(double[][] matriz1, double[][] matriz2) {
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

	public void decompLU(double[][] matriz) {
		 
	}

    public double[][] inverteMat(double[][] teste) {
		System.out.println("det? " + determinante);
		if (determinante == 0) {
            throw new ArithmeticException("Matriz singular!");
        }

		inversa = new double[matriz.length][matriz.length];

		int it = 5;

		this.inversa = identidade;

		while (!equals(matriz, identidade) && it > 0) {
			
			it--;
		}

		return inversa;
    }
}
