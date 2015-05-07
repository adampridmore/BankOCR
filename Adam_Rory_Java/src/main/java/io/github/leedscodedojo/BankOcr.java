package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

public class BankOcr {
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

    public String parseSingleOcrCharacter(String characterToScan) {
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

    public String read(String textToScan) {
        String[] lines = getLines(textToScan);

        StringBuilder scannedCharacters = new StringBuilder();

        for (int i = 0; i < lines[0].length(); i += 3) {
            String ocrCharacter = createOcrCharacterFromThreeLines(
                    lines[0].substring(i, i + 3),
                    lines[1].substring(i, i + 3),
                    lines[2].substring(i, i + 3));

            String parsedCharacter = parseSingleOcrCharacter(ocrCharacter);
            scannedCharacters.append(parsedCharacter);
        }

        return scannedCharacters.toString();
    }

    private String createOcrCharacterFromThreeLines(String line1, String line2, String line3) {
        return String.format("%s\n%s\n%s\n", line1, line2, line3);
    }

    private String[] getLines(String textToScan) {
        String[] lines = textToScan.split("\n");
        assertLines(textToScan, lines);
        return lines;
    }

    private void assertLines(String textToScan, String[] lines) {
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

    public boolean isValidChecksumAccountNumber(String accountNumber) {
        List<Integer> accountDigits = accountNumberTestToDigits(accountNumber);

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

    public String read2(String accountNumber) {
        if (!isLegalAccountNumber(accountNumber)){
            return String.format("%s ILL", accountNumber);
        }

        if (isValidChecksumAccountNumber(accountNumber)) {
            return accountNumber;
        } else {
            return String.format("%s ERR", accountNumber);
        }
    }

    private boolean isLegalAccountNumber(String accountNumber) {
        if (accountNumber.contains("?")){
            return false;
        }else {
            return true;
        }
    }
}
