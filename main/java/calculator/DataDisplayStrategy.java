package calculator;

import java.math.BigDecimal;

public interface DataDisplayStrategy {
    void displayData(BigDecimal dividend, BigDecimal divisor, String sign,
                     StringBuilder quotient, StringBuilder result, int[] index, int tab);
}
