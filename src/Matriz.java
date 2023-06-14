public class Matriz {
    double[][] matriz;
    double[][] indentidade = {
            {1,0,0},
            {0,1,0},
            {0,0,1}
    };
    double determinante;


    public Matriz(double[][] matriz) {
        this.matriz = matriz;
        determinante = det(matriz);
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

			for (int j = 1; j < matriz.length; j++) {
				for (int k = 0; k < matriz.length; k++) {
					if (k < i) {
						mat_temp[j - 1][k] = matriz[j][k];
					} else if (k > i) {
						mat_temp[j - 1][k - 1] = matriz[j][k];
					}
				}
			}

			resultado += matriz[0][i] * Math.pow (-1, (i + 1)) * det(mat_temp);
		}
		return resultado;
	}

    public double inversa() {
        return 0;
    }
}
