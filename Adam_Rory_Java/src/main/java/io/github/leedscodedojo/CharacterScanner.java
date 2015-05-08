package io.github.leedscodedojo;

public class CharacterScanner {
    public final static String UNKNOWN = "?";

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


    public static ParsedOcrCharacter parseSingleOcrCharacter(String characterToScan) {
        if (characterToScan.equals(ZERO)) {
            return new ParsedOcrCharacter("0");
        }
        if (characterToScan.equals(ONE)) {
            return new ParsedOcrCharacter("1");
        }
        if (characterToScan.equals(TWO)) {
            return new ParsedOcrCharacter("2");
        }
        if (characterToScan.equals(THREE)) {
            return new ParsedOcrCharacter("3");
        }
        if (characterToScan.equals(FOUR)) {
            return new ParsedOcrCharacter("4");
        }
        if (characterToScan.equals(FIVE)) {
            return new ParsedOcrCharacter("5");
        }
        if (characterToScan.equals(SIX)) {
            return new ParsedOcrCharacter("6");
        }
        if (characterToScan.equals(SEVEN)) {
            return new ParsedOcrCharacter("7");
        }
        if (characterToScan.equals(EIGHT)) {
            return new ParsedOcrCharacter("8");
        }
        if (characterToScan.equals(NINE)) {
            return new ParsedOcrCharacter("9");
        }

        System.out.println(String.format("Unknown character:%n%s", characterToScan));

        return new ParsedOcrCharacter(UNKNOWN);
    }
}
