import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Aux;

public class App {
    public static void main(String[] args) throws Exception {
        String fileName = "src/casos de teste.txt";
        BufferedReader reader = null;

        List<Double> listaFx = new ArrayList<>();
        List<List<Double>> listaMatriz = new ArrayList<>();
        List<Integer> listaSimbolos = new ArrayList<>();
        List<Double> listaCb = new ArrayList<>();
        
        boolean max = false;

        try {
            reader = new BufferedReader(new FileReader(fileName));

            //checkpoint
            String funcaoObjeto = reader.readLine();
            if (funcaoObjeto.toLowerCase().startsWith("max")) {
                max = true;
                System.out.println("MAX");
            }else {
                System.out.println("MIN");
            }

            String filtro = "";
            for (int i = 0; i < funcaoObjeto.length() && funcaoObjeto.charAt(i) != '='; i++) {
                filtro += funcaoObjeto.charAt(i);
            }

            String funcaoComFiltro = funcaoObjeto.replaceAll(Pattern.quote(filtro), "");

            // checkpoint
            listaFx = extrairCoeficientes(funcaoComFiltro);

            String line;
            Pattern pattern = Pattern.compile("([<>≤≥=]+)\\s*(-?\\d+(\\.\\d+)?)");

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().startsWith("xi")) {
                    continue;
                }

                listaMatriz.add(extrairCoeficientes(line));

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    String operador = matcher.group(1);
                    String valueStr = matcher.group(2);
        
                    double value = Double.parseDouble(valueStr);

                    listaCb.add(value);

                    switch (operador) {
                        case "<":
                            listaSimbolos.add(-2);
                            break;
                        case "<=":
                        case "\u2264":
                            listaSimbolos.add(-1);
                            break;
                        case "=":
                            listaSimbolos.add(0);
                            break;
                        case ">=":
                        case "\u2265":
                            listaSimbolos.add(1);
                            break;
                        case ">":
                            listaSimbolos.add(2);
                            break;
                    }
                } 
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        {
            Matriz matriz = new Matriz(); // Funções de matriz

            double[] fx = new double[listaFx.size()];

            for (int i = 0; i < fx.length; i++) {
                fx[i] = listaFx.get(i);
            }

            double[][] A = new double[listaMatriz.size()][listaMatriz.get(0).size()];
            double[] b = new double[listaCb.size()];
            int[] simbolos = new int[listaSimbolos.size()];

            System.out.println("fx: " + listaFx);
            System.out.println("simbolos: " + listaSimbolos);
            System.out.println("b: " + listaCb);

            for (int i = 0; i < A.length; i++) {
                List<Double> list = listaMatriz.get(i);

                for (int j = 0; j < A[0].length; j++) {
                    A[i][j] = list.get(j);
                }

                b[i] = listaCb.get(i);
            }

            Aux.print("A: ", A);

            Simplex s = new Simplex(matriz, max, fx, A, simbolos);

        }
        
        /*
        Scanner scanner = new Scanner(System.in);
        Matriz matriz = new Matriz(); // Funções de matriz

        int[] simbolos;
        double[] fx;
        double[][] A;

        System.out.print("Digite o tamanho da matriz: \n>> ");
        int tam = Integer.parseInt(scanner.nextLine());  // User input
        System.out.println();

        A = new double[tam][tam]; // Alocando espaço
        simbolos = new int[tam];
        fx = new double[tam];

        boolean max = false; // Definindo max e min

        System.out.print("(min/max) \n>> ");
        String tipoFuncao = scanner.next();
        System.out.println();

        if (tipoFuncao.length() >= 3 && tipoFuncao.substring(0, 3).equals("max")) {
            max = true;
        }

        //*
        for (int i = 0; i < A.length; i++) {
            System.out.print("Valor de x" + (i + 1) + "\n>> ");
            fx[i] = Double.parseDouble(scanner.nextLine());
        }

        System.out.println();

        for(int i = 0; i < tam; i++) {
            for(int j = 0; j < tam - 1; j++) {
                System.out.print("Valor de a" + (i + 1) + (j + 1) + "\n>> ");
                A[i][j] = Double.parseDouble(scanner.nextLine());
            }

            System.out.print("\nSinal do sistema \n>> ");

            String input = scanner.next();

            if (input.length() == 2) {

                char char1 = input.charAt(0);
                char char2 = input.charAt(1);

                if (char1 == '<') {
                    simbolos[i]++;
                    if(char2 == '=') {
                        simbolos[i]++;
                    }
                }else if (char1 == '>') {
                    simbolos[i]--;
                    if(char2 == '=') {
                        simbolos[i]--;
                    }
                }

            } else if (input.length() == 1) {
                char char1 = input.charAt(0);

                if (char1 == '<') {
                    simbolos[i]++;
                }else if (char1 == '>') {
                    simbolos[i]--;
                }
            } else {
                System.out.println("Digite um ou dois caracteres.");
            }

            System.out.print("\nValor de a" + (i + 1) + tam + "\n>> ");
            A[i][tam - 1] = Double.parseDouble(scanner.nextLine());
        }

        System.out.println();

        System.out.println("det(A) = " + matriz.det(A) + "\n\n");
        Aux.print("A:", A);

        Simplex s = new Simplex(matriz, max, fx, A, simbolos);
        scanner.close();
        // */
       
    }



    public static List<Double> extrairCoeficientes(String equacao) {
        String teste = equacao.replaceAll("\\s", "");
        Pattern pattern = Pattern.compile("([-+]?(\\d*\\.?\\d*|\\d+))[a-zA-Z]\\d*");
        Matcher matcher = pattern.matcher(removeU2212(teste));

        List<Double> coeficientes = new ArrayList<>();

        while (matcher.find()) {
            String coefStr = matcher.group(1);

            // Tratar coeficientes vazios ou apenas sinais de adição/subtração
            double c = (coefStr.isEmpty() || coefStr.equals("+")) ? 1.0 :
                    (coefStr.equals("-") ? -1.0 : Double.parseDouble(coefStr));

            coeficientes.add(c);
        }

        return coeficientes;
    }

    public static String removeU2212(String equacao) {
        String textoAlterado = "";

        for (int i = 0; i < equacao.length(); i++) {
            textoAlterado +=  equacao.charAt(i) == '\u2212' ? '-' : equacao.charAt(i);
        }

        return textoAlterado;
    }
}
