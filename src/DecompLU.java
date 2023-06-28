public class DecompLU {
    double[][] l;
	double[][] u;

    public DecompLU(double[][] matriz) {
        l = new double[matriz.length][matriz.length];
        u = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            for (int k = i; k < matriz.length; k++) { // U
                double soma = 0;

                for (int j = 0; j < i; j++) {
					soma += (l[i][j] * u[j][k]);
				}
 
                u[i][k] = matriz[i][k] - soma;
            }
 
            for (int k = i; k < matriz.length; k++) { // L
                if (i == k) {
                    l[i][i] = 1;
				}else {
                    double soma = 0;
                    for (int j = 0; j < i; j++) {
                        soma += (l[k][j] * u[j][i]);
					}

                    l[k][i] = (matriz[k][i] - soma) / u[i][i];
                }
            }
        }

        
    }

    public double[] resolveSistema(double[] b) {
        double[] y = new double[b.length];
        double[] x = new double[b.length];

		int i, j;

        for (i = 0; i < b.length; i++) {
            double soma = 0;
            for(j = 0; j <= i - 1; j++) {
                soma += l[i][j] * y[j];
            }
            
            y[j] = b[i] - soma;
        }

        for (i = b.length - 1; i >= 0; i--) {
            double soma = 0;
            for(j = i + 1; j < b.length; j++) {
                soma += u[i][j] * x[j];
            }
           
            x[i] = (y[i] - soma) / u[i][i];
        }

        return x;
    }

        
}
