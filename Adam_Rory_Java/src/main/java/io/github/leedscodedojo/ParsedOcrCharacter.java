package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

public class ParsedOcrCharacter {
    private String character;

    public ParsedOcrCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

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

        return new ParsedOcrCharacter("?");
    }

    public static List<String> getAlternativeOcrCharacters(String ocrCharacters) {
        ArrayList<String> alternatives = new ArrayList<String>();
        for(int y = 0 ; y < 3 ; y++){
            for(int x = 0 ; x < 3 ; x++){
                alternatives.addAll(toggleOcrSegment(ocrCharacters, x,y));
            }
        }

        return alternatives;
    }

    public static List<String> toggleOcrSegment(String ocrCharacter, int x, int y) {
        char[] originalChars = ocrCharacter.toCharArray();

        char[] allSegments = new char[]{' ','|', '_'};

        ArrayList<String> alternativeSegments = new ArrayList<String>();

        for(char c : allSegments){
            if (originalChars[y * 4 + x] != c){
                char[] newChars = ocrCharacter.toCharArray();
                newChars[y * 4 + x] = c;

                alternativeSegments.add(new String(newChars));
            }
        }

        return alternativeSegments;
    }

    public static List<ParsedOcrCharacter> getValidAlternativeDigits(String ocrCharacters) {
        ArrayList<ParsedOcrCharacter> alternativeValidDigits = new ArrayList<ParsedOcrCharacter>();

        List<String> alternativeOcrCharacters = getAlternativeOcrCharacters(ocrCharacters);
        for(String alternativeOcrCharacter : alternativeOcrCharacters){
            ParsedOcrCharacter scannedDigit = parseSingleOcrCharacter(alternativeOcrCharacter);
            if (!scannedDigit.isUnknownCharacter()){
                alternativeValidDigits.add(scannedDigit);
            }
        };

        return alternativeValidDigits;
    }

    public boolean isUnknownCharacter() {
        return (getCharacter().equals("?"));
    }
}
