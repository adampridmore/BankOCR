package io.github.leedscodedojo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BankOcrTest {
    @Test
    public void can_parse_12() {
        String textToScan =
                "    _ \n" +
                "  | _|\n" +
                "  ||_ \n";

        BankOcr bankOcr = new BankOcr();
        String actualNumber = bankOcr.read(textToScan);

        assertThat(actualNumber, is("12"));
    }

    @Test
    public void can_parse_1234() {
        String textToScan =
                "    _  _    \n" +
                "  | _| _||_|\n" +
                "  ||_  _|  |\n";

        BankOcr bankOcr = new BankOcr();
        String actualNumber = bankOcr.read(textToScan);

        assertThat(actualNumber, is("1234"));
    }

    @Test
    public void can_parse_0123456789() {
        String textToScan =
                " _     _  _     _  _  _  _  _ \n" +
                "| |  | _| _||_||_ |_   ||_||_|\n" +
                "|_|  ||_  _|  | _||_|  ||_| _|\n";

        BankOcr bankOcr = new BankOcr();
        String actualNumber = bankOcr.read(textToScan);

        assertThat(actualNumber, is("0123456789"));
    }
    @Test
    public void single_line_is_invalid() {
       String textToScan = "XXX";

        BankOcr bankOcr = new BankOcr();
        try {
            bankOcr.read(textToScan);
        }catch (Throwable e){
            assertThat(e.getMessage(), containsString("XXX"));
        }
    }

    @Test
    public void different_length_lines_throws_exception() {
        String textToScan = "XXXXXX\n" +
                "XXX\n" +
                "XXX\n";

        BankOcr bankOcr = new BankOcr();
        try {
            bankOcr.read(textToScan);
        }catch (Throwable e){
            assertThat(e.getMessage(), containsString("XXX"));
        }
    }

    @Test
    public void lines_must_be_multiple_of_three_long() {
        String textToScan = "XXXX\n" +
                "XXXX\n" +
                "XXXX\n";

        BankOcr bankOcr = new BankOcr();
        try {
            bankOcr.read(textToScan);
        }catch (Throwable e){
            assertThat(e.getMessage(), containsString("XXX"));
        }
    }

    @Test
    public void invalid_digit() {
        String textToScan =
                "XXX\n" +
                "XXX\n" +
                "XXX\n";

        BankOcr bankOcr = new BankOcr();
        String actualNumber = bankOcr.read(textToScan);

        assertThat(actualNumber, is("?"));
    }

    @Test
    public void validate_account_number_for_valid_account(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.isValidChecksumAccountNumber("345882865"), is(true));
    }

    @Test
    public void validate_account_number_for_invalid_account(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.isValidChecksumAccountNumber("245882865"), is(false));
    }

    @Test
    public void parse_account_with_validation_for_valid_account(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.read2("457508000"), is("457508000"));
    }

    @Test
    public void parse_account_with_validation_for_invalid_account(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.read2("664371495"), is("664371495 ERR"));
    }

    @Test
    public void parse_account_with_validation_for_account_with_invalid_characters(){
        BankOcr bankOcr = new BankOcr();
        assertThat(bankOcr.read2("86110??36"), is("86110??36 ILL"));
    }
}