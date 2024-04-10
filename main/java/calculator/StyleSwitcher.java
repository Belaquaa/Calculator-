package calculator;

import calculator.divisionStyles.ClassicDataDisplayStrategy;
import calculator.dataProcessing.DivisionStyle;
import calculator.divisionStyles.GermanDataDisplayStrategy;

public class StyleSwitcher {
    private static DataDisplayStrategy classic = new ClassicDataDisplayStrategy();
    private static DataDisplayStrategy german = new GermanDataDisplayStrategy();

    public static DataDisplayStrategy displayStyle(DivisionStyle style){
        if (style == DivisionStyle.GERMAN){
            return german;
        } return classic;
    }
}
