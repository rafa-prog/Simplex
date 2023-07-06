import utils.Aux;

public class Simplex {
    boolean max;
    int tamN;
    int[] index;
    double[] cB, b;
    double[][] B, BInversa, N;

    public Simplex(double[] cB, double[][] matriz, double[] simbolos) {
        max = false;
        tamN = 0;

        Matriz m = new Matriz();

        //this.cB = cB;

        for (int i = 0; i < simbolos.length; i++) {
            if(simbolos[i] != 0)
                tamN++;
        }

        index = new int[matriz.length + matriz.length - 1];

        for (int i = 0; i < index.length; i++) {
            index[i] = i + 1;
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

        //B[matriz.length - 1][matriz.length - 1] = simbolos[0]; // DEF [x1 x2 x5]
        
        B[0][matriz.length - 1] = simbolos[0]; // DEF [x1 x2 x3]

        // DEF [x4 xi] 
        for (int i = 1; i <= N[0].length; i++) {
            N[i][i - 1] = simbolos[i];
        }

        BInversa = m.inversa(B);
        Aux.printMatriz("A:", matriz);
        Aux.printMatriz("B:", B);
        Aux.printMatriz("N:", N);
        Aux.printVetor("b:", b);
        Aux.printVetor("cB", cB);
        Aux.printMatriz("B⁻¹:", BInversa);
        

        double[] xChapeu = new double[B.length + tamN - 1];

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B.length; j++) {
                xChapeu[i] += BInversa[i][j] * b[j];
            }

            if (xChapeu[i] < 0) {
                throw new RuntimeException("Sistema Ax = b fere a condição de não-negatividade");
            }
        }

        Aux.printVetor("xChapeu", xChapeu);

        double[] lambda_t = new double[B.length];

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < lambda_t.length; j++) {
                lambda_t[i] += cB[j] * BInversa[j][i];
            }
        }

        Aux.printVetor("lambda", lambda_t);

        double[] y = new double[tamN - 1];

        for (int i = 0; i < y.length; i++) {
            double res = 0;

            for (int j = 0; j < matriz.length; j++) {
                res += lambda_t[j] * N[j][i];
            }

            y[i] = xChapeu[i + tamN] - res;
            
            if (y[i] < 0) {
                System.out.println("fudeo");
            }
        }

        

        Aux.printVetor("y:", y);
    }

    private double[] resolver() {
        double[] a = null;
        return a;
    }
}
