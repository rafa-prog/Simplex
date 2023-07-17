package utils;

public interface Aux {

	public static void print (String txt, double[][] matriz) {

		System.out.println(txt);

		for(double[] linha : matriz) {
			print("$", linha);
		}

		System.out.println();
	}


	public static void print (String txt, double[] vetor) {

		if(txt != "$") {
			System.out.println(txt);
		}

		System.out.print("[ ");

		for(double valor : vetor) {
			if (valor >= 0) {
				System.out.print(" ");
			}
			
			if (valor == 0) {
				System.out.print("0.0 ");
			} else {
				System.out.print(valor + " ");
			}
		}

		System.out.println(" ]");

		if(txt != "$") {
			System.out.println();
		}
	}


	public static int fat (int num) {
		int res = num;
        if (num > 1) {
            return res * fat(num - 1);
        }
		return res;
    }


	public static double min(double[] vetor) {
		double res = 0;

		for (double valor : vetor) {
			if (valor <= res && valor > 0) {
				res = valor;
			}
		}

		return res;
	}


	public static double[] concatenarVetores(double[] vetor1, double[] vetor2) {
		double[] res = new double[vetor1.length + vetor2.length];

		for (int i = 0; i < res.length; i++) {
			if(i < vetor1.length) {
				res[i] = vetor1[i];
			}else {
				res[i] = vetor2[i - vetor1.length];
			}
		}
		return res;
	}

	
	public static boolean compararMatrizes(double[][] matriz1, double[][] matriz2) {
		if (matriz1.length != matriz2.length) {
			throw new ArithmeticException("Tamanho diferente entre matrizes!");
		}

		for (int i = 0; i < matriz1.length; i++) {
			for (int j = 0; j < matriz1.length; j++) {
				if (matriz1[i][j] != matriz2[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
	

	public static double multiplicar(double[] vetor1, double[] vetor2) {
		if(vetor1.length != vetor2.length) {
			throw new ArithmeticException("Tamanho diferente entre vetores!");
		}

		double res = 0;

		for (int i = 0; i < vetor1.length; i++) {
			res += vetor1[i] * vetor2[i];
		}

		return res;
	}

	
	public static double[] multiplicar (double[] vetor, double[][] matriz) {
		if(vetor.length != matriz.length) {
			throw new ArithmeticException("Tamanho diferente entre vetor e matriz!");
		}

		double[] res = new double[vetor.length];

		for (int i = 0; i < matriz[0].length; i++) {
			for (int j = 0; j < vetor.length; j++) {
				res[i] += vetor[j] * matriz[j][i];
			}
		}

		return res;
	}


	public static double[][] multiplicar (double[][] matriz1, double[][] matriz2) {
		if (matriz1.length != matriz2[0].length) {
			throw new ArithmeticException("Tamanho diferente entre matrizes!");
		}

		double[][] res = new double[matriz1.length][matriz1.length];
		
		for (int i = 0; i < matriz1.length; i++) {
			for (int k = 0; k < matriz1.length; k++) {
				for (int j = 0; j < matriz1.length; j++) {
					res[i][j] += matriz1[i][k] * matriz2[k][j];
				}
			}
		}

		return res;
	}


	public static double[][] verifPivoNulo (double[][] matriz, int it) {
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
				if (i != linha) {
					continue;
				}
				
				double aux;
				troca = i;

				for (int j = 0; j < matriz[0].length; j++) {
					aux = matriz[i][j];
					matriz[i][j] = matriz[linha][j];
					matriz[linha][j] = aux;
				}
			}
		}

		print("L" + (linha + 1) + " <-> L" + (troca + 1), matriz);

		// Confere se precisa de mais trocas
		verifPivoNulo(matriz, it);

		return matriz;
	}


	public static double[][] trocaCol(double[][] matriz, int colInicial, int colFinal) {

		for (int i = 0; i < matriz.length; i++) {
			double aux = matriz[i][colInicial];
			matriz[i][colInicial] = matriz[i][colFinal];
			matriz[i][colFinal] = aux;
		}

		return matriz;
	}
}
