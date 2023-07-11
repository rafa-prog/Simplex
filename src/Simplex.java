import utils.Aux;

public class Simplex {
    boolean max;
    int tamN;
    double[] cB, cR, b;
    double[][] B, BInversa, N;

    public Simplex(double[] cB, double[][] matriz, double[] simbolos) {

        max = false;
        tamN = 0;

        cR = resolver(cB, matriz, simbolos, 1);
    }

    private void setTest(Matriz m) {
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

        BN = Aux.trocaCol(BN, 3, 5);

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

    private void procede(double[] y) {
        System.out.println("procede a dar errado");
    }

    private double[] resolver(double[] cB, double[][] matriz, double[] simbolos, int it) {
        double[] result = new double[cB.length];
        Matriz m = new Matriz();

        //this.cB = cB;

        for (int i = 0; i < simbolos.length; i++) {
            if(simbolos[i] != 0)
                tamN++;
        }

        B = new double[matriz.length][matriz[0].length];
        N = new double[matriz.length][matriz[0].length - 1];
        b = new double[matriz.length];

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

        setTest(m);

        BInversa = m.inversa(B);
        Aux.print("A:", matriz);
        Aux.print("B:", B);
        Aux.print("N:", N);
        Aux.print("b:", b);
        Aux.print("cB", cB);
        Aux.print("B⁻¹:", BInversa);
        

        double[] xChapeu = Aux.multiplicar(b, BInversa);// BInversa[i][j] * b[j];
        Aux.print("xChapeu", xChapeu);

        for (int i = 0; i < B.length; i++) {
            if (xChapeu[i] < 0) {
                //throw new RuntimeException("Sistema Ax = b fere a condição de não-negatividade");
            }
        }

        double[] lambda_t = Aux.multiplicar(cB, BInversa);
        Aux.print("lambda", lambda_t);


        int k = -1;
        double[] y = ;
        

        for (int i = 0; i < y.length; i++) {
            double res = 0;

            for (int j = 0; j < matriz.length; j++) {
                res += lambda_t[j] * N[j][i];
            }

            y[i] = xChapeu[i + tamN] - res;
            
            if (y[i] < 0) {
                k = i;
                procede(y);
            }
        }

        Aux.print("y:", y);

        return result;
    }
}
