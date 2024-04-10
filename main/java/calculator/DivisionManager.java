package calculator;

import calculator.helperMethods.IdenticalCharsString;
import calculator.helperMethods.LineIndexes;
import calculator.dataProcessing.DivisionStyle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivisionManager {


    // тело деления
    public static String makeDivision(BigDecimal signDividend, BigDecimal signDivisor) {
        return makeDivision(signDividend, signDivisor, DivisionStyle.CLASSIC);
    }
    
    public static String makeDivision(BigDecimal signDividend, BigDecimal signDivisor, DivisionStyle style) {

        BigDecimal dividend = signDividend.abs();
        BigDecimal divisor = signDivisor.abs();
        String sign = (signDividend.signum() * signDivisor.signum() >= 0) ? "" : "-";

        StringBuilder result = new StringBuilder();
        StringBuilder quotient = new StringBuilder();
        StringBuilder reminder = new StringBuilder();

        if (divisor.compareTo(BigDecimal.ZERO) == 0) throw new IllegalArgumentException("Cannot divide by zero.");
        if (dividend.compareTo(divisor) < 0) return (dividend + " : " + sign + divisor + " = 0");

        for (int i = 0; i < dividend.precision(); i++) {
            reminder.append(dividend.toPlainString().charAt(i));
            BigDecimal reminderNumber = new BigDecimal(reminder.toString());

            if (reminderNumber.compareTo(divisor) >= 0) {
                BigDecimal newReminder = reminderNumber.remainder(divisor);
                BigDecimal divisionResult = reminderNumber.divide(divisor, 0, RoundingMode.DOWN);
                BigDecimal productOfDivisionResultAndDivisor = divisionResult.multiply(divisor);

                appendDivisionStep(result, i, reminderNumber, productOfDivisionResultAndDivisor, divisor);

                quotient.append(divisionResult); // добавление к частному
                reminder = new StringBuilder(newReminder.toPlainString()); // обновление остатка

            } else if (i >= divisor.precision()) quotient.append(0);
        }
        result.append(String.format("%" + (dividend.precision() + 1) + "s", reminder)).append("\n");
        initialDataDisplay(dividend, divisor, sign, quotient, result, style);
        return result.toString();
    }

    // шаг деления
    private static void appendDivisionStep(StringBuilder result, int i, BigDecimal reminderNumber,
                                           BigDecimal multiplyResult, BigDecimal divisor) {

        String lastReminder = String.format("%" + (i + 2) + "s", "_" + reminderNumber); // _{число}
        String subtractNumber = String.format("%" + (i + 2) + "s", multiplyResult);     // число, которое мы отнимаем
        result.append(lastReminder).append("\n");
        result.append(subtractNumber).append("\n");
        result.append(
                IdenticalCharsString.assemblyString(lastReminder.length() - divisor.precision(), ' ') +
                        IdenticalCharsString.assemblyString(divisor.precision(), '-')).append("\n");

    }

    // отображение начальных данных
    private static void initialDataDisplay(BigDecimal dividend, BigDecimal divisor, String sign,
                                           StringBuilder quotient, StringBuilder result, DivisionStyle style) {

        int[] index = LineIndexes.findNewLineIndexes(result);
        int tab = dividend.precision() + 1 - index[0];

        DataDisplayStrategy strategy = StyleSwitcher.displayStyle(style);
        strategy.displayData(dividend, divisor, sign, quotient, result, index, tab);
        result.replace(1, index[0], dividend.toPlainString());
    }

}
