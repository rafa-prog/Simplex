import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teste {
    public static void main(String[] args) {
        String inequality = "2x1 − x2 + 3x3 ≤   4 e 3x1 + 5x2 = 10";

        // Usando expressão regular para encontrar o operador (<=, >=, =) e o valor (4 ou 10)
        Pattern pattern = Pattern.compile("([<>≤≥=]+)\\s*(-?\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(inequality);

        while (matcher.find()) {
            String operator = matcher.group(1);
            String valueStr = matcher.group(2);

            double value = Double.parseDouble(valueStr);

            System.out.println("Operador: " + operator);
            System.out.println("Valor: " + value);
        }
    }
}
