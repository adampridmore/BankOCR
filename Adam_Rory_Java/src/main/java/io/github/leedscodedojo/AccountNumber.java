package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

public class AccountNumber {
    public String getAccountNumberText() {
        return accountNumberText;
    }

    private String accountNumberText;

    private AccountNumber(String accountNumberText) {
        this.accountNumberText = accountNumberText;
    }

    public static AccountNumber createFromAccountNumberText(String accountNumberText) {
        return new AccountNumber(accountNumberText);
    }

    public static AccountNumber createFromOcrString(String ocrText) {
        return createFromOcrString(new OcrText(ocrText));
    }

    public static AccountNumber createFromOcrString(OcrText ocrText) {
        List<OcrCharacter> ocrCharacters = ocrText.readOcrCharacters();

        StringBuilder scannedCharacters = new StringBuilder();

        for(OcrCharacter ocrCharacter : ocrCharacters){
            ParsedOcrCharacter parsedOcrCharacter = ocrCharacter.parse();
            String accountNumberCharacter = parsedOcrCharacter.getCharacter();
            scannedCharacters.append(accountNumberCharacter);
        }

        return new AccountNumber(scannedCharacters.toString());
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
        char[] chars = accountNumberText.toCharArray();
        for (char c : chars) {
            int digit = Integer.parseInt(Character.toString(c));
            accountDigits.add(digit);
        }
        return accountDigits;
    }

    private boolean hasIllegalDigits() {
        if (accountNumberText.contains("?")){
            return true;
        }else {
            return false;
        }
    }

    public String getAccountNumberTextWithStatus() {
        if (hasIllegalDigits()){
            return String.format("%s ILL", accountNumberText);
        }

        if (isChecksumValid()) {
            return accountNumberText;
        } else {
            return String.format("%s ERR", accountNumberText);
        }
    }
}
