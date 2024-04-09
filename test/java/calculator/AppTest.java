package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;

/**
 * Unit test for App
 */
public class AppTest {

    // WholeNumber tests
    @Test
    void getScale_positiveScale() {
        Assertions.assertEquals(2, WholeNumber.getScale(new BigDecimal("2000.12"), new BigDecimal("80")));
        Assertions.assertEquals(4, WholeNumber.getScale(new BigDecimal("2000.22"), new BigDecimal("80.1234")));
        Assertions.assertEquals(1, WholeNumber.getScale(new BigDecimal("2000.2"), new BigDecimal("80.800")));
    }

    @Test
    void getScale_negativeScale() {
        Assertions.assertEquals(-1, WholeNumber.getScale(new BigDecimal("2000"), new BigDecimal("80")));
        Assertions.assertEquals(-2, WholeNumber.getScale(new BigDecimal("2000"), new BigDecimal("800")));
        Assertions.assertEquals(-4, WholeNumber.getScale(new BigDecimal("200000"), new BigDecimal("80000")));
    }

    @Test
    void getScale_zeroScale() {
        Assertions.assertEquals(0, WholeNumber.getScale(new BigDecimal("2000.000"), new BigDecimal("88")));
        Assertions.assertEquals(0, WholeNumber.getScale(new BigDecimal("2000.000"), new BigDecimal("88.00")));
        Assertions.assertEquals(0, WholeNumber.getScale(new BigDecimal("20.000"), new BigDecimal("11.0")));
    }

    @Test
    void getWholeNumber_positiveScale() {
        Assertions.assertEquals(new BigDecimal("20022"), WholeNumber.getWholeNumber(new BigDecimal("200.22"), 2));
        Assertions.assertEquals(new BigDecimal("2002"), WholeNumber.getWholeNumber(new BigDecimal("200.200"), 1));
        Assertions.assertEquals(new BigDecimal("-2002"), WholeNumber.getWholeNumber(new BigDecimal("-200.200"), 1));
        Assertions.assertEquals(new BigDecimal("-20002"), WholeNumber.getWholeNumber(new BigDecimal("-2.0002"), 4));
    }

    @Test
    void getWholeNumber_negativeScale() {
        Assertions.assertEquals(new BigDecimal("22"), WholeNumber.getWholeNumber(new BigDecimal("220"), -1));
        Assertions.assertEquals(new BigDecimal("2"), WholeNumber.getWholeNumber(new BigDecimal("200"), -2));
        Assertions.assertEquals(new BigDecimal("-2"), WholeNumber.getWholeNumber(new BigDecimal("-200"), -2));
        Assertions.assertEquals(new BigDecimal("23"), WholeNumber.getWholeNumber(new BigDecimal("230000"), -4));
    }

    @Test
    void getWholeNumber_zeroScale() {
        Assertions.assertEquals(new BigDecimal("2002"), WholeNumber.getWholeNumber(new BigDecimal("2002"), 0));
        Assertions.assertEquals(new BigDecimal("321"), WholeNumber.getWholeNumber(new BigDecimal("321.00"), 0));
        Assertions.assertEquals(new BigDecimal("-321"), WholeNumber.getWholeNumber(new BigDecimal("-321.00"), 0));
        Assertions.assertEquals(new BigDecimal("0"), WholeNumber.getWholeNumber(new BigDecimal("0.00"), 0));
        Assertions.assertEquals(new BigDecimal("0"), WholeNumber.getWholeNumber(new BigDecimal("-0.00"), 0));
    }

    // Division tests
    @Test
    void makeDivision_zero() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Division.makeDivision(new BigDecimal("2024"), new BigDecimal("0"));
                });
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Division.makeDivision(new BigDecimal("-2024"), new BigDecimal("-0"));
                });
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Division.makeDivision(new BigDecimal("0"), new BigDecimal("0"));
                });

    }

    @Test
    void makeDivision_min() {
        Assertions.assertEquals("2 : 4 = 0",
                Division.makeDivision(new BigDecimal("2"), new BigDecimal("4")));
        Assertions.assertEquals("213 : -214 = 0",
                Division.makeDivision(new BigDecimal("-213"), new BigDecimal("214")));
        Assertions.assertEquals("213 : -214 = 0",
                Division.makeDivision(new BigDecimal("213"), new BigDecimal("-214")));
        Assertions.assertEquals("213 : 214 = 0",
                Division.makeDivision(new BigDecimal("-213"), new BigDecimal("-214")));
        Assertions.assertEquals("0 : 4 = 0",
                Division.makeDivision(new BigDecimal("+0"), new BigDecimal("4")));
    }

    @Test
    void makeDivision_normal_classic() {
        Assertions.assertEquals(
                "_78945│4\n" +
                        " 4    │-----\n" +
                        " -    │19736\n" +
                        "_38\n" +
                        " 36\n" +
                        "  -\n" +
                        " _29\n" +
                        "  28\n" +
                        "   -\n" +
                        "  _14\n" +
                        "   12\n" +
                        "    -\n" +
                        "   _25\n" +
                        "    24\n" +
                        "     -\n" +
                        "     1\n", Division.makeDivision(new BigDecimal("78945"), new BigDecimal("4")));
        Assertions.assertEquals(
                "_12│-4\n" +
                        " 12│--\n" +
                        "  -│-3\n" +
                        "  0\n", Division.makeDivision(new BigDecimal("-12"), new BigDecimal("4")));
        Assertions.assertEquals(
                "_100│2\n" +
                        " 10 │--\n" +
                        "  - │50\n" +
                        "  00\n", Division.makeDivision(new BigDecimal("100"), new BigDecimal("2")));
        Assertions.assertEquals(
                "_22000│-12\n" +
                        " 12   │-----\n" +
                        " --   │-1833\n" +
                        "_100\n" +
                        "  96\n" +
                        "  --\n" +
                        "  _40\n" +
                        "   36\n" +
                        "   --\n" +
                        "   _40\n" +
                        "    36\n" +
                        "    --\n" +
                        "     4\n", Division.makeDivision(new BigDecimal("-22000"), new BigDecimal("12")));
    }

    @Test
    void makeDivision_german_classic() {
        Assertions.assertEquals(
                "_78945 : 4 = 19736\n" +
                        " 4\n" +
                        " -\n" +
                        "_38\n" +
                        " 36\n" +
                        "  -\n" +
                        " _29\n" +
                        "  28\n" +
                        "   -\n" +
                        "  _14\n" +
                        "   12\n" +
                        "    -\n" +
                        "   _25\n" +
                        "    24\n" +
                        "     -\n" +
                        "     1\n",
                Division.makeDivision(new BigDecimal("78945"), new BigDecimal("4"), Division.DivisionStyle.GERMAN));
        Assertions.assertEquals(
                "_12 : -4 = -3\n" +
                        " 12\n" +
                        "  -\n" +
                        "  0\n",
                Division.makeDivision(new BigDecimal("-12"), new BigDecimal("4"), Division.DivisionStyle.GERMAN));
        Assertions.assertEquals(
                "_100 : 2 = 50\n" +
                        " 10\n" +
                        "  -\n" +
                        "  00\n",
                Division.makeDivision(new BigDecimal("100"), new BigDecimal("2"), Division.DivisionStyle.GERMAN));
        Assertions.assertEquals(
                "_22000 : -12 = -1833\n" +
                        " 12\n" +
                        " --\n" +
                        "_100\n" +
                        "  96\n" +
                        "  --\n" +
                        "  _40\n" +
                        "   36\n" +
                        "   --\n" +
                        "   _40\n" +
                        "    36\n" +
                        "    --\n" +
                        "     4\n",
                Division.makeDivision(new BigDecimal("-22000"), new BigDecimal("12"), Division.DivisionStyle.GERMAN));
    }

    // InputService test
    @Test
    void inputService() {
        System.setIn(new ByteArrayInputStream("10 2 germaN".getBytes()));
        Assertions.assertEquals(new BigDecimal("10"), InputService.getDividend());
        Assertions.assertEquals(new BigDecimal("2"), InputService.getDivisor());
        Assertions.assertEquals("GERMAN", InputService.getStyle());
    }
}
