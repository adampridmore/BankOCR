package io.github.leedscodedojo;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OcrCharacterTest {
    @Test
    public void get_alternative_characters_for_ocr_digit(){
        String ocrCharacters =  "   \n" +
                                "   \n" +
                                "   \n";
        List<String> alternativeCharacters = OcrCharacter.getAlternativeOcrCharacters(ocrCharacters);

        assertThat(alternativeCharacters.size(),is(18));

        for(String text : alternativeCharacters){
            System.out.println("===");
            System.out.print(text);
        }
        System.out.println("===");
    }

    @Test
    public void get_alternative_digits_for_ocr_digit(){
        String ocrCharacters =
                "   \n" +
                "  |\n" +
                "   \n";
        List<String> alternativeDigits = OcrCharacter.getValidAlternativeDigits(ocrCharacters);

        assertThat(alternativeDigits.size(),is(1));
        assertThat(alternativeDigits.get(0), is("1"));
    }

    @Test
    public void toggle_ocr_segment(){
        String ocrCharacter =
                "   \n" +
                "   \n" +
                "   \n";
        List<String> toggledOcrCharacters = OcrCharacter.toggleOcrSegment(ocrCharacter, 0,0);

        String expectedCharacter1 =
                "|  \n" +
                "   \n" +
                "   \n";
        String expectedCharacter2 =
                "_  \n" +
                "   \n" +
                "   \n";
        assertThat(toggledOcrCharacters.get(0), is(expectedCharacter1));
        assertThat(toggledOcrCharacters.get(1), is(expectedCharacter2));
    }

    @Test
    public void toggle_another_ocr_segment(){
        String ocrCharacter =
                "   \n" +
                "   \n" +
                "   \n";
        List<String> toggledOcrCharacters = OcrCharacter.toggleOcrSegment(ocrCharacter, 1,2);

        String expectedCharacter =
                "   \n" +
                "   \n" +
                " | \n";
        assertThat(toggledOcrCharacters.get(0), is(expectedCharacter));
    }

    @Test
    public void scratch(){

    }
}