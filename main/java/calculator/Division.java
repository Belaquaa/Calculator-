package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division {

    public enum DivisionStyle {
        CLASSIC,
        GERMAN
    }

    public static String makeDivision(BigDecimal signDividend, BigDecimal signDivisor) {
        return makeDivision(signDividend, signDivisor, DivisionStyle.CLASSIC);
    }

    public static String makeDivision(BigDecimal signDividend, BigDecimal signDivisor, DivisionStyle style) {
        if (signDivisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Делить на ноль нельзя :(");
        }

        BigDecimal dividend = signDividend.abs();
        BigDecimal divisor = signDivisor.abs();
        String sign = (signDividend.signum() * signDivisor.signum() >= 0) ? "" : "-";
        StringBuilder result = new StringBuilder();

        if (dividend.compareTo(divisor) < 0) {
            return dividend + " : " + sign + divisor + " = 0";
        }

        StringBuilder quotient = new StringBuilder();
        StringBuilder reminder = new StringBuilder();
        for (int i = 0; i < dividend.precision(); i++) {
            reminder.append(dividend.toPlainString().charAt(i));
            BigDecimal reminderNumber = new BigDecimal(reminder.toString());

            if (reminderNumber.compareTo(divisor) >= 0) {
                BigDecimal mod = reminderNumber.remainder(divisor);
                BigDecimal multiplyResult = reminderNumber.divide(divisor, 0, RoundingMode.DOWN).multiply(divisor);
                appendDivisionStep(result, i, reminderNumber, multiplyResult, divisor);
                quotient.append(reminderNumber.divide(divisor, 0, RoundingMode.DOWN)); // добавление к частному
                reminder = new StringBuilder(mod.toPlainString()); // обновление остатка
            } else {
                if (i >= divisor.precision()) {
                    quotient.append(0);
                }
            }
        }
        result.append(String.format("%" + (dividend.precision() + 1) + "s", reminder)).append("\n");
        initialDataDisplay(result, dividend, divisor, sign, quotient, style);
        return result.toString();
    }

    private static void appendDivisionStep(StringBuilder result, int i, BigDecimal reminderNumber,
                                           BigDecimal multiplyResult, BigDecimal divisor) {
        String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber); // это где _{число}
        result.append(lastReminder).append("\n");
        result.append(String.format("%" + (i + 2) + "s", multiplyResult)).append("\n"); // это число, которое мы отнимаем
        int tab = lastReminder.length() - divisor.precision(); // это сколько минусов и пробелов
        result.append(assemblyString(tab, ' ') +
                assemblyString(divisor.precision(), '-')).append("\n");

    }

    private static void initialDataDisplay(StringBuilder result, BigDecimal dividend, BigDecimal divisor,
                                           String sign, StringBuilder quotient, DivisionStyle style) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                index[j++] = i;
                if (j == 3) break;
            }
        }

        int tab = dividend.precision() + 1 - index[0];
        switch (style) {
            case CLASSIC:
                result.insert(index[2], assemblyString(tab, ' ') + "│" + sign + quotient); // это частное
                result.insert(index[1], assemblyString(tab, ' ') + "│" + sign +
                        assemblyString(Math.max(quotient.length(), divisor.precision()), '-')); // это минусы
                result.insert(index[0], "│" + sign + divisor); // это делитель
                break;
            case GERMAN:
                result.insert(index[0], " : " + sign + divisor + " = " + sign + quotient);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный стиль!");
        }
        result.replace(1, index[0], dividend.toPlainString());
    }

    private static String assemblyString(int numberOfSymbols, char symbol) { // строка из повторяющихся символов
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numberOfSymbols; i++) {
            string.append(symbol);
        }
        return string.toString();
    }
}