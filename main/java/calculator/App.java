package calculator;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        // initial data
        BigDecimal dividend = InputService.getDividend();
        BigDecimal divisor = InputService.getDivisor();
        String getStyle = InputService.getStyle();

        DivisionStyle style = DivisionStyle.CLASSIC;

        // transform data via class Division
        int scale = WholeNumber.getScale(dividend, divisor);
        dividend = WholeNumber.getWholeNumber(dividend, scale);
        divisor = WholeNumber.getWholeNumber(divisor, scale);

        // fit DivisionStyle from class Division
        try {
            style = DivisionStyle.valueOf(getStyle);
        } catch (IllegalArgumentException e) {
            System.err.println("Стиль '" + getStyle +
                    "' не соответствует значениям DivisionStyle. Применён стиль по умолчанию.");
        }

        // make it work
        System.out.println(Division.makeDivision(dividend, divisor, style));
    }
}