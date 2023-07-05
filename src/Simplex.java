import utils.Aux;

public class Simplex {
    boolean max;
    int tamN;
    int[] index;
    double[] fx, b;
    double[][] B, BInversa, N;

    public Simplex(double[][] matriz, double[] simbolos) {
        max = false;
        fx = new double[matriz.length];
        fx[0] = -2;
        fx[1] = -1;
        tamN = 0;

        Matriz m = new Matriz();

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

        B[0][0] = 1;
        B[0][1] = 1;
        B[0][2] = 0;

        B[1][0] = 1;
        B[1][1] = 0;
        B[1][2] = 0;

        B[2][0] = 0;
        B[2][1] = 1;
        B[2][2] = 1;

        b[0] = 4;
        b[1] = 3;
        b[2] = (double) 7/2;

        index[2] = 5;
        index[3] = 3;
        index[4] = 4;
        
        //B[0][matriz.length - 1] = simbolos[0]; // DEF [x1 x2 x3]

        // DEF [x4 xi] 
        for (int i = 1; i <= N[0].length; i++) {
            //## dps tirar prim +1
            N[i - 1][i - 1] = simbolos[i];
        }

        Aux.printMatriz("N:",N);

        BInversa = m.inversa(B);

        Aux.printMatriz("B", B);
        Aux.printMatriz("B⁻¹", BInversa);
        Aux.printVetor("b:", b);

        double[] xChapeu = new double[B.length + tamN - 1];

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B.length; j++) {
                xChapeu[i] += BInversa[i][j] * b[j];
            }

            //Aux.printVetor("xChapeu:", xChapeu);
        }


        double[] lambda_t = new double[B.length];

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < lambda_t.length; j++) {
                lambda_t[i] += fx[j] * BInversa[i][j];
            }
        }

        Aux.printVetor("lambdaTb", lambda_t);
    }
}
