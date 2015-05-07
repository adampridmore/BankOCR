package io.github.leedscodedojo;

public class OcrCharacter {

    private static final String ZERO =
            " _ \n" +
            "| |\n" +
            "|_|\n";

    private static final String ONE =
            "   \n" +
            "  |\n" +
            "  |\n";

    private static final String TWO =
            " _ \n" +
            " _|\n" +
            "|_ \n";

    private static final String THREE =
            " _ \n" +
            " _|\n" +
            " _|\n";

    private static final String FOUR =
            "   \n" +
            "|_|\n" +
            "  |\n";

    private static final String FIVE =
            " _ \n" +
            "|_ \n" +
            " _|\n";

    private static final String SIX =
            " _ \n" +
            "|_ \n" +
            "|_|\n";

    private static final String SEVEN =
            " _ \n" +
            "  |\n" +
            "  |\n";

    private static final String EIGHT =
            " _ \n" +
            "|_|\n" +
            "|_|\n";

    private static final String NINE =
            " _ \n" +
            "|_|\n" +
            " _|\n";

    public static String parseSingleOcrCharacter(String characterToScan) {
        if (characterToScan.equals(ZERO)) {
            return "0";
        }
        if (characterToScan.equals(ONE)) {
            return "1";
        }
        if (characterToScan.equals(TWO)) {
            return "2";
        }
        if (characterToScan.equals(THREE)) {
            return "3";
        }
        if (characterToScan.equals(FOUR)) {
            return "4";
        }
        if (characterToScan.equals(FIVE)) {
            return "5";
        }
        if (characterToScan.equals(SIX)) {
            return "6";
        }
        if (characterToScan.equals(SEVEN)) {
            return "7";
        }
        if (characterToScan.equals(EIGHT)) {
            return "8";
        }
        if (characterToScan.equals(NINE)) {
            return "9";
        }

        System.out.println(String.format("Unknown character:%n%s", characterToScan));

        return "?";
    }
}
