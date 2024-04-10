package calculator.dataProcessing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WholeNumber {

    public static BigDecimal getWholeNumber(BigDecimal number, int scale) {
        return number.scaleByPowerOfTen(scale).setScale(0, RoundingMode.UNNECESSARY);
    }

    public static int getScale(BigDecimal dividend, BigDecimal divisor) {
        return Math.max(
                dividend.stripTrailingZeros().scale(),
                divisor.stripTrailingZeros().scale());
    }
}