package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigDecimal;

public class InputService {

    private static final String DEFAULT_STYLE = "CLASSIC";
    private static String[] calculationData;

    static {
        loadData();
    }

    private InputService() {
        throw new IllegalStateException("Utility class");
    }

    public static BigDecimal getDividend() {
        return toBigDecimal(calculationData[0]);
    }

    public static BigDecimal getDivisor() {
        return toBigDecimal(calculationData[1]);
    }

    public static String getStyle() {
        return calculationData.length == 3 ? calculationData[2].trim().toUpperCase() : DEFAULT_STYLE;
    }

    private static void loadData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите параметры для деления (делимое, делитель, стиль (опционально)):");
            calculationData = parseInput(reader.readLine());
        } catch (IOException e) {
            System.err.println("Ошибка в вводе данных: " + e.getMessage());
            System.exit(1);
        }
    }

    private static String[] parseInput(String input) throws IOException {
        String[] data = input.trim().split("\\s+");
        if (data.length < 2 || data.length > 3) {
            throw new IOException("Неверное количество аргументов");
        }
        return data;
    }

    private static BigDecimal toBigDecimal(String input) {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка в преобразовании данных: " + input);
        }
    }
}