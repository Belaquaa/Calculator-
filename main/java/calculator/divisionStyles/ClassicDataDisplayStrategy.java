package calculator.divisionStyles;

import calculator.helperMethods.IdenticalCharsString;
import calculator.DataDisplayStrategy;

import java.math.BigDecimal;

public class ClassicDataDisplayStrategy implements DataDisplayStrategy {
    @Override
    public void displayData(BigDecimal dividend, BigDecimal divisor, String sign,
                            StringBuilder quotient, StringBuilder result, int[] index, int tab) {
        result.insert(index[2], IdenticalCharsString.assemblyString(tab, ' ') + "│" + sign + quotient);
        result.insert(index[1], IdenticalCharsString.assemblyString(tab, ' ') + "│" + sign +
                IdenticalCharsString.assemblyString(Math.max(quotient.length(), divisor.precision()), '-'));
        result.insert(index[0], "│" + sign + divisor);
    }
}