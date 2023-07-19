import utils.Aux;

public class Simplex {
    boolean max;
    int tamN;
    double[] cB, cR, b, n, xChapeu;
    double[][] B, N, BInversa; 

    public Simplex(boolean max, double[] cB, double[][] matriz, int[] simbolos) {
        this.cB = cB;

        if(max) {
            for (int i = 0; i < cB.length; i++) {
                cB[i] = cB[i] * (-1);
            }
        }

        tamN = 0;

        for (int i = 0; i < simbolos.length; i++) {
            if (simbolos[i] != 0) {
                tamN++;
            }
        }

        B = new double[matriz.length][matriz[0].length];
        N = new double[matriz.length][matriz[0].length - 1];

        b = new double[matriz.length];
        n = new double[matriz.length - 1];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (j < matriz.length - 1) {
                    B[i][j] = matriz[i][j];
                }else {
                    b[i] = matriz[i][j];
                }
            }
        }

        B[matriz.length - 1][matriz.length - 1] = simbolos[simbolos.length - 1]; // DEF [x1 x2 xn]

        // DEF [x3 xi (i < n)] 
        for (int i = 0; i < N[0].length; i++) {
            N[i][i] = simbolos[i];
        }

        cR = resolver(simbolos, 1);
    }

    private void trocaColunas(int col1, int col2) {
        double[][] BN = new double[B.length][B[0].length + N[0].length];

        for (int i = 0; i < BN.length; i++) {
            for (int j = 0; j < BN[0].length; j++) {
                if (j < B.length) {
                    BN[i][j] = B[i][j];
                    
                }else {
                    BN[i][j] = N[i][j - B.length];
                }
            }
        }

        BN = Aux.trocaCol(BN, col1, col2);

        for (int i = 0; i < BN.length; i++) {
            for (int j = 0; j < BN[0].length; j++) {
                if (j < B.length) {
                    B[i][j] = BN[i][j];
                    
                }else {
                    N[i][j - B.length] = BN[i][j];
                }
            }
        }
    }

    private double[] resolver(int[] simbolos, int it) {
        System.out.println("teste " + it);

        Matriz m = new Matriz();
        BInversa = m.inversa(B);

        Aux.print("B:", B);
        Aux.print("N:", N);
        Aux.print("b:", b);
        Aux.print("n:", n);
        Aux.print("cB", cB);
        Aux.print("B⁻¹:", BInversa);

        xChapeu = Aux.concatenarVetores(Aux.multiplicar(b, BInversa), n);// b_chapeu e n_chapeu

        Aux.print("xChapeu", xChapeu);

        for (int i = 0; i < B.length; i++) {
            if (xChapeu[i] < 0) {
                //throw new RuntimeException("Sistema Ax = b fere a condição de não-negatividade");
            }
        }

        double[] lambda_t = Aux.multiplicar(cB, BInversa);
        Aux.print("lambda", lambda_t);

        double[] res = Aux.multiplicar(lambda_t, N);
        double[] y = new double[N[0].length];

        int kMenor = -1;
        double menor = Double.POSITIVE_INFINITY;

        for (int i = 0; i < y.length; i++) {
            y[i] = xChapeu[i + tamN] - res[i];
            
            if (y[i] < menor) {
                menor = y[i];
                kMenor = i;
            }
        }

        Aux.print("y: ", y);

        double[] direcao = new double[B.length];

        for (int i = 0; i < B.length; i++) {
            direcao[i] = N[i][kMenor];
        }

        direcao = Aux.multiplicar(direcao, BInversa); 
        
        Aux.print("direcao: ", direcao);

        if (menor < 0) {
            if (it > Aux.fat(B.length) || it <= 0) {
			    throw new RuntimeException("Sistema sem solução");
		    }

            it++;

            double[] tamanho_passo = new double[B.length];

            int kMin = -1;
            double min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < tamanho_passo.length; i++) {
                if (B[i][kMenor] > 0 && direcao[i] != 0 && (xChapeu[i] / direcao[i]) >= 0) {
                    tamanho_passo[i] = xChapeu[i] / direcao[i];
                    if (tamanho_passo[i] < min) {
                        min = tamanho_passo[i];
                        kMin = i;
                    }
                }
            }

            trocaColunas(kMenor, kMin);

            y = resolver(simbolos, it);
        }

        return y;
    }
}