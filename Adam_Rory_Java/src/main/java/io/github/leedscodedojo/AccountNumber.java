package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

public class AccountNumber {
    private List<ParsedOcrCharacter> parsedOcrCharacters = new ArrayList<ParsedOcrCharacter>();

    private AccountNumber(String accountNumberText) {
        for(char c : accountNumberText.toCharArray()){
            ParsedOcrCharacter parsedOcrCharacter = new ParsedOcrCharacter(Character.toString(c));
            parsedOcrCharacters.add(parsedOcrCharacter);
        }
    }

    private AccountNumber(List<ParsedOcrCharacter> parsedOcrCharacters){
        this.parsedOcrCharacters = new ArrayList<ParsedOcrCharacter>(parsedOcrCharacters);
    }

    public String getAccountNumberText(){
        StringBuilder sb = new StringBuilder();
        for(ParsedOcrCharacter parsedOcrCharacter : parsedOcrCharacters){
            sb.append(parsedOcrCharacter.getCharacter());
        }
        return sb.toString();
    }

    public static AccountNumber createFromAccountNumberText(String accountNumberText) {
        return new AccountNumber(accountNumberText);
    }

    public static AccountNumber createFromOcrString(String ocrText) {
        return createFromOcrString(new OcrText(ocrText));
    }

    public static AccountNumber createFromOcrString(OcrText ocrText) {
        List<OcrCharacter> ocrCharacters = ocrText.readOcrCharacters();

        List<ParsedOcrCharacter> parsedOcrCharacters = new ArrayList<ParsedOcrCharacter>();

        for(OcrCharacter ocrCharacter : ocrCharacters){
            parsedOcrCharacters.add(ocrCharacter.parse());
        }

        return new AccountNumber(parsedOcrCharacters);
    }

    public boolean isChecksumValid() {
        List<Integer> accountDigits = accountNumberTextToDigits();

        int checkSum = 0;
        int digitIndex = 0;
        for (Integer digit : accountDigits) {
            checkSum += (9 - digitIndex) * digit;
            digitIndex++;
        }

        return checkSum % 11 == 0;
    }

    private List<Integer> accountNumberTextToDigits() {
        List<Integer> accountDigits = new ArrayList<Integer>();
        char[] chars = getAccountNumberText().toCharArray();
        for (char c : chars) {
            int digit = Integer.parseInt(Character.toString(c));
            accountDigits.add(digit);
        }
        return accountDigits;
    }

    private boolean hasIllegalDigits() {
        for(ParsedOcrCharacter parsedOcrCharacter : parsedOcrCharacters){
            if (parsedOcrCharacter.isUnknownCharacter()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAmbiguosDigits() {
        for(ParsedOcrCharacter parsedOcrCharacter : parsedOcrCharacters){
            if (parsedOcrCharacter.isAmbiguous()) {
                return true;
            }
        }
        return false;
    }

    public String getAccountNumberTextWithStatus() {
        if (hasAmbiguosDigits()) {
            return String.format("%s AMB", getAccountNumberText());
        }

        if (hasIllegalDigits()){
            return String.format("%s ILL", getAccountNumberText());
        }

        if (isChecksumValid()) {
            return getAccountNumberText();
        } else {
            return String.format("%s ERR", getAccountNumberText());
        }
    }
}
