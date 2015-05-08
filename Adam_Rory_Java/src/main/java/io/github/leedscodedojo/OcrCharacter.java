package io.github.leedscodedojo;

import java.util.List;

public class OcrCharacter {
    private String text;

    public OcrCharacter(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ParsedOcrCharacter parse() {
        ParsedOcrCharacter parsedCharacter = ParsedOcrCharacter.parseSingleOcrCharacter(getText());

        if (parsedCharacter.isUnknownCharacter()) {
            List<ParsedOcrCharacter> alternativeOcrCharacters = ParsedOcrCharacter.getValidAlternativeDigits(getText());
            if (alternativeOcrCharacters.size() == 0) {

            }else if (alternativeOcrCharacters.size() == 1) {
                return alternativeOcrCharacters.get(0);
            }else {
                parsedCharacter.setAmbiguous(true);
                logTooManyDigits(alternativeOcrCharacters);
            }
        }

        return parsedCharacter;
    }

    private void logTooManyDigits(List<ParsedOcrCharacter> alternativeOcrCharacters) {
        StringBuilder alternativeCharactersText = new StringBuilder();
        for(ParsedOcrCharacter c : alternativeOcrCharacters){
            alternativeCharactersText.append(String.format("%s,",c.getCharacter()));
        }
        alternativeCharactersText.deleteCharAt(alternativeCharactersText.length() - 1);
        System.out.println("Too many alternative digits: " + alternativeCharactersText.toString() );
    }
}