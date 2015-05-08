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
            if (alternativeOcrCharacters.size() == 1) {
                return alternativeOcrCharacters.get(0);
            }
        }

        return parsedCharacter;
    }
}