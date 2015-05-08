package io.github.leedscodedojo;

import java.util.ArrayList;
import java.util.List;

public class OcrText {
    private String text;

    public OcrText(String text) {
        this.text = text;
    }

    public String[] getLines() {
        String[] lines = text.split("\n");
        assertLines(text, lines);
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

    public List<OcrCharacter> readOcrCharacters() {
        String[] lines = getLines();

        ArrayList<OcrCharacter> blocks = new ArrayList<OcrCharacter>();

        for (int i = 0; i < lines[0].length(); i += 3) {
            String line1 = lines[0].substring(i, i + 3);
            String line2 = lines[1].substring(i, i + 3);
            String line3 = lines[2].substring(i, i + 3);

            String ocrBlockText =  createOcrCharacterFromThreeLines(line1, line2,line3);
            blocks.add(new OcrCharacter(ocrBlockText));
        }

        return blocks;
    }

    private static String createOcrCharacterFromThreeLines(String line1, String line2, String line3) {
        return String.format("%s\n%s\n%s\n", line1, line2, line3);
    }
}
