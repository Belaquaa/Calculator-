package calculator.helperMethods;

public class IdenticalCharsString {
    public static String assemblyString(int numberOfSymbols, char symbol) { // строка из повторяющихся символов
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }
}
