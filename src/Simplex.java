import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import utils.Aux;

public class Simplex {
    boolean max;
    int tamN;
    //Set<List<Integer>> listaNegra;
    Deque<Troca> trocas;
    double[] cB, cR, b, n, xChapeu;
    double[][] B, N, BInversa; 

    public Simplex(Matriz m, boolean max, double[] cB, double[][] matriz, int[] simbolos) {
        this.cB = cB;
        tamN = 0;

        if(max) {
            for (int i = 0; i < cB.length; i++) {
                cB[i] = cB[i] * (-1);
            }
        }

        for (int i = 0; i < simbolos.length; i++) {
            if (simbolos[i] != 0) {
                tamN++;
            }
        }

        B = new double[matriz.length][matriz[0].length];
        N = new double[matriz.length][matriz[0].length - 1];

        b = new double[matriz.length];
        n = new double[matriz.length - 1];

        //listaNegra = new HashSet<>();

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

        /*
        List<Integer> indexList = Aux.listarTroca(index, B[0].length - 1, index.length - 1);

        listaNegra.add(indexList);

        System.out.println(listaNegra);
        //*/

        trocas = new ArrayDeque<>();

        cR = resolver(m, simbolos, 1);
    }

    private double[] resolver(Matriz m, int[] simbolos, int it) {
        if (it > Aux.fat(B.length) || it <= 0) {
			throw new RuntimeException("Sistema sem solução");
		}

        if(m.det(B) == 0 && trocas.isEmpty()) {
            if(it == 1) {

                int col1 = (B[0].length + N[0].length) / 2;
                int col2 = col1 + 1;

                while(m.det(B) == 0 && it <= Aux.fat(B.length) && it >= 0) {
                    gerenciaColunas(col1, col2);
                    Troca troca = new Troca(col1, col2, null);
                    trocas.add(troca);

                    col1 = (col1 - 1) % (B[0].length + N[0].length);
                    col2 = (col2 + 1) % (B[0].length + N[0].length);
                }

                if (it > Aux.fat(B.length)) {
                    throw new RuntimeException("Sistema sem solução");
                }
            }else {
                throw new RuntimeException("Sistema sem solução");
            }
            
        }

        System.out.println("Iteração " + it + ":");

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

        return calculaProximoValor(m, lambda_t, simbolos, it);
    }

    public void gerenciaColunas(int col1, int col2) {
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

        BN = Aux.trocaColunasMatriz(BN, col1, col2);

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

    public double[] calculaProximoValor(Matriz m, double[] lambda_t, int[] simbolos, int it) {
        double[] res = Aux.multiplicar(lambda_t, N);
        double[] y = new double[N[0].length];

        int kMenor = -1;
        double menor = Double.POSITIVE_INFINITY;
        
        int validadorMenor = -1;

        if(!trocas.isEmpty()) {
            Troca trocaAnterior = trocas.peekLast();
            validadorMenor = trocaAnterior.getCol1();
        }

        for (int i = 0; i < y.length; i++) {
            y[i] = xChapeu[i + tamN] - res[i];

            if (y[i] < menor && i != validadorMenor) {
                menor = y[i];
                kMenor = i;
            }
        }

        if(menor == Double.POSITIVE_INFINITY) {
            throw new RuntimeException("Sistema sem solução");
        }

        Aux.print("y: ", y);

        double[] direcao = new double[B.length];

        for (int i = 0; i < B.length; i++) {
            direcao[i] = N[i][kMenor];
        }

        direcao = Aux.multiplicar(direcao, BInversa); 
        Aux.print("direcao: ", direcao);

        if (menor < 0) {

            it++;

            double[] tamanho_passo = new double[B.length];

            int kMin = -1;
            double min = Double.POSITIVE_INFINITY;
            boolean validadorMin = true;

            for (int i = 0; i < tamanho_passo.length; i++) {
                if (B[i][kMenor] > 0 && direcao[i] != 0 && (xChapeu[i] / direcao[i]) >= 0) {
                    tamanho_passo[i] = xChapeu[i] / direcao[i];

                    if (tamanho_passo[i] < min && validadorMin) {
                        min = tamanho_passo[i];
                        kMin = i;
                    }

                    if(!trocas.isEmpty()) {
                        Troca verficaTroca = trocas.peekLast();
                        List<Double> valoresProibidos = verficaTroca.getListaNegra();

                        if(!valoresProibidos.isEmpty()) {

                            for (Double valor : valoresProibidos) {
                                if (min >= valor && verficaTroca.getCol2() != kMin) {
                                    validadorMin = false;
                                    break;
                                }   
                            }
                        }
                    }
                }

                if(!validadorMin) {
                    break;
                }else {
                    min = Double.POSITIVE_INFINITY;
                }
            }   
            
            if(min < Double.POSITIVE_INFINITY) {
                gerenciaColunas(kMenor, kMin);

                List<Double> listaNegra = new ArrayList<>();
                listaNegra.add(min);

                Troca troca = new Troca(kMenor, kMin, listaNegra);

                if(m.det(B) == 0) {
                    it--;

                    gerenciaColunas(kMenor, kMin);

                    System.out.println("volta troca: " + kMenor + " " + kMin);
                }else {
                    trocas.add(troca);
                }
            }else {
                if(!trocas.isEmpty()) {
                    trocas.pop();
                }else {
                    throw new RuntimeException("Sistema sem solução");
                }
            }

            y = resolver(m, simbolos, it);
        }

        return y;
    }
}