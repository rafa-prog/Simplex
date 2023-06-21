public class Matriz {
	double determinante;
    double[][] matriz;
	double[][] inversa;
    double[][] identidade;

    public Matriz(double[][] matriz) {
        this.matriz = matriz;
        determinante = det(matriz);

		identidade = new double[matriz.length][matriz.length + 1];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if(i == j) {
					identidade[i][j] = 1; 
				}else {
					identidade[i][j] = 0;
				}
			}
		}
    }


	public void print() {
		for(double[] linha : matriz) {
			for(double col_linha : linha) {
				System.out.println(col_linha + ", ");
			}
		}
	}

    private double det(double[][] matriz) {
		double[][] mat_temp;
		double resultado = 0;

		if (matriz.length == 1) {
			resultado = matriz[0][0];
			return resultado;
		}

		for (int i = 0; i < matriz.length; i++) {
			mat_temp = new double[matriz.length - 1][matriz.length - 1];
			for (int k = 0; k < matriz.length; k++) {
				for (int j = 1; j < matriz.length; j++) {
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

	public double[][] multiplicaMat(double[][] matriz2) {
		if(matriz.length != matriz2.length) {
			throw new ArithmeticException("Tamanho diferente entre matrizes!");
		}

		double[][] mat_result = new double[matriz.length][matriz.length];
		
		for (int i = 0; i < matriz.length; i++) {
			for (int k = 0; k < matriz.length; k++) {
				for (int j = 0; j < matriz.length; j++) {
					mat_result[i][j] += matriz[i][k] * matriz2[k][j];
				}
			}
		}

		return mat_result;
	}

	public boolean equals(double[][] matriz2) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(matriz[i][j] != matriz2[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

    public double inversa() {
		if(determinante == 0) {
            throw new ArithmeticException("Matriz singular!");
        }

		System.out.println("teste");

		if(equals(identidade)) {

		}else {

		}

        return 0;
    }
}
