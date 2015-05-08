package io.github.leedscodedojo;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OcrTextTest {
    @Test
    public void readCharacter(){
        OcrText ocrText = new OcrText(
                "XXXYYY\n" +
                "XXXYYY\n" +
                "XXXYYY\n");

        List<OcrCharacter> ocrCharacter = ocrText.readOcrCharacters();

        assertThat(ocrCharacter.size(), is(2));
        assertThat(ocrCharacter.get(0).getText(), is("XXX\nXXX\nXXX\n"));
        assertThat(ocrCharacter.get(1).getText(), is("YYY\nYYY\nYYY\n"));
    }
}