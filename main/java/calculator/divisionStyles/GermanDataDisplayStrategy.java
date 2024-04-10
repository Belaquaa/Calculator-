package calculator.divisionStyles;

import calculator.DataDisplayStrategy;

import java.math.BigDecimal;

public class GermanDataDisplayStrategy implements DataDisplayStrategy {
    @Override
    public void displayData(BigDecimal dividend, BigDecimal divisor, String sign,
                            StringBuilder quotient, StringBuilder result, int[] index, int tab) {
        result.insert(index[0], " : " + sign + divisor + " = " + sign + quotient);
    }
}
