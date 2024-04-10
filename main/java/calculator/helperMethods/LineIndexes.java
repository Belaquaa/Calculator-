package calculator.helperMethods;

public class LineIndexes {
    public static int[] findNewLineIndexes(StringBuilder result) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < result.length(); i++) {
            if (result.charAt(i) == '\n') {
                index[j++] = i;
                if (j == 3) break;
            }
        }
        return index;
    }
}
