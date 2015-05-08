package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

public class ParsedOcrCharacter {
    private String character;

    private boolean ambiguous = false;

    public ParsedOcrCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
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
            ParsedOcrCharacter scannedDigit = CharacterScanner.parseSingleOcrCharacter(alternativeOcrCharacter);
            if (!scannedDigit.isUnknownCharacter()){
                alternativeValidDigits.add(scannedDigit);
            }
        }

        return alternativeValidDigits;
    }

    public boolean isUnknownCharacter() {
        return (getCharacter().equals(CharacterScanner.UNKNOWN));
    }

    public boolean isAmbiguous() {
        return ambiguous;
    }

    public void setAmbiguous(boolean ambiguous) {
        this.ambiguous = ambiguous;
    }
}
