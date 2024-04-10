package calculator;

import calculator.dataProcessing.DivisionStyle;
import calculator.dataProcessing.InputService;
import calculator.dataProcessing.WholeNumber;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        // initial data
        BigDecimal dividend = InputService.getDividend();
        BigDecimal divisor = InputService.getDivisor();
        DivisionStyle style = DivisionStyle.valueOf(InputService.getStyle());

        // normalize data
        int scale = WholeNumber.getScale(dividend, divisor);
        dividend = WholeNumber.getWholeNumber(dividend, scale);
        divisor = WholeNumber.getWholeNumber(divisor, scale);

        // make it work
        System.out.println(DivisionManager.makeDivision(dividend, divisor, style));
    }
}