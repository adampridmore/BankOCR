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
        String[] lines = getLines(ocrText);

        StringBuilder scannedCharacters = new StringBuilder();

        for (int i = 0; i < lines[0].length(); i += 3) {
            String ocrCharacter = createOcrCharacterFromThreeLines(
                    lines[0].substring(i, i + 3),
                    lines[1].substring(i, i + 3),
                    lines[2].substring(i, i + 3));

            String parsedCharacter = OcrCharacter.parseSingleOcrCharacter(ocrCharacter);
            scannedCharacters.append(parsedCharacter);
        }

        return new AccountNumber(scannedCharacters.toString());
    }

    private static String createOcrCharacterFromThreeLines(String line1, String line2, String line3) {
        return String.format("%s\n%s\n%s\n", line1, line2, line3);
    }

    private static String[] getLines(String textToScan) {
        String[] lines = textToScan.split("\n");
        assertLines(textToScan, lines);
        return lines;
    }

    private static void assertLines(String textToScan, String[] lines) {
        if (lines.length != 3) {
            throw new RuntimeException("Incorrect number of lines in: " + textToScan);
        }

        if (lines[0].length() % 3 != 0) {
            throw new RuntimeException("Lines lengths must be a multiple of three: " + textToScan);
        }

        if (lines[0].length() != lines[1].length() || lines[1].length() != lines[2].length()) {
            throw new RuntimeException("Lines are different lengths: " + textToScan);
        }
    }

    public boolean isChecksumValid() {
        List<Integer> accountDigits = accountNumberTestToDigits(accountNumberText);

        int checkSum = 0;
        int digitIndex = 0;
        for (Integer digit : accountDigits) {
            checkSum += (9 - digitIndex) * digit;
            digitIndex++;
        }

        return checkSum % 11 == 0;
    }

    private List<Integer> accountNumberTestToDigits(String accountNumber) {
        List<Integer> accountDigits = new ArrayList<Integer>();
        char[] chars = accountNumber.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                int digit = Integer.parseInt(Character.toString(c));
                accountDigits.add(digit);
            } else {
                accountDigits.add(null);
            }
        }
        return accountDigits;
    }

    private boolean isLegalAccountNumber() {
        if (accountNumberText.contains("?")){
            return false;
        }else {
            return true;
        }
    }

    public String getAccountNumberTextWithStatus() {
        if (!isLegalAccountNumber()){
            return String.format("%s ILL", accountNumberText);
        }

        if (isChecksumValid()) {
            return accountNumberText;
        } else {
            return String.format("%s ERR", accountNumberText);
        }
    }
}
