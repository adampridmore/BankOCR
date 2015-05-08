package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> getValidAlternativeDigits(String ocrCharacters) {
        ArrayList<String> alternativeValidDigits = new ArrayList<String>();

        List<String> alternativeOcrCharacters = getAlternativeOcrCharacters(ocrCharacters);
        for(String alternativeOcrCharacter : alternativeOcrCharacters){
            String scannedDigit = parseSingleOcrCharacter(alternativeOcrCharacter);
            if (!scannedDigit.equals("?")){
                alternativeValidDigits.add(scannedDigit);
            }
        };

        return alternativeValidDigits;
    }
}
