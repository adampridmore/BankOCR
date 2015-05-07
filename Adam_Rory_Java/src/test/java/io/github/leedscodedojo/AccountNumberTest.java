package io.github.leedscodedojo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccountNumberTest {
    @Test
    public void can_parse_12() {
        String textToScan =
                "    _ \n" +
                "  | _|\n" +
                "  ||_ \n";

        AccountNumber accountNumber = AccountNumber.createFromOcrString(textToScan);
        assertThat(accountNumber.getAccountNumberText(), is("12"));
    }

    @Test
    public void can_parse_1234() {
        String textToScan =
                "    _  _    \n" +
                "  | _| _||_|\n" +
                "  ||_  _|  |\n";

        AccountNumber accountNumber = AccountNumber.createFromOcrString(textToScan);
        assertThat(accountNumber.getAccountNumberText(), is("1234"));
    }

    @Test
    public void can_parse_0123456789() {
        String textToScan =
                " _     _  _     _  _  _  _  _ \n" +
                "| |  | _| _||_||_ |_   ||_||_|\n" +
                "|_|  ||_  _|  | _||_|  ||_| _|\n";

        AccountNumber accountNumber = AccountNumber.createFromOcrString(textToScan);
        assertThat(accountNumber.getAccountNumberText(), is("0123456789"));
    }

    @Test
    public void single_line_is_invalid() {
        String textToScan = "XXX";

        try {
            AccountNumber.createFromOcrString(textToScan);
        } catch (Throwable e) {
            assertThat(e.getMessage(), containsString("XXX"));
        }
    }

    @Test
    public void different_length_lines_throws_exception() {
        String textToScan =
                "XXXXXX\n" +
                "XXX\n" +
                "XXX\n";

        try {
            AccountNumber.createFromOcrString(textToScan);
        } catch (Throwable e) {
            assertThat(e.getMessage(), containsString("XXX"));
        }
    }

    @Test
    public void lines_must_be_multiple_of_three_long() {
        String textToScan =
                "XXXX\n" +
                "XXXX\n" +
                "XXXX\n";

        try {
            AccountNumber.createFromOcrString(textToScan);
        } catch (Throwable e) {
            assertThat(e.getMessage(), containsString("XXX"));
        }
    }

    @Test
    public void invalid_digit() {
        String textToScan =
                "XXX\n" +
                "XXX\n" +
                "XXX\n";

        AccountNumber accountNumber = AccountNumber.createFromOcrString(textToScan);

        assertThat(accountNumber.getAccountNumberText(), is("?"));
    }

    @Test
    public void validate_account_number_for_valid_account(){
        AccountNumber accountNumber = AccountNumber.createFromAccountNumberText("345882865");
        assertThat(accountNumber.isChecksumValid(), is(true));
    }

    @Test
    public void validate_account_number_for_invalid_account(){
        AccountNumber accountNumber = AccountNumber.createFromAccountNumberText("245882865");
        assertThat(accountNumber.isChecksumValid(), is(false));
    }

    @Test
    public void parse_account_with_validation_for_valid_account(){
        AccountNumber accountNumber = AccountNumber.createFromAccountNumberText("457508000");
        assertThat(accountNumber.getAccountNumberTextWithStatus(), is("457508000"));
    }

    @Test
    public void parse_account_with_validation_for_invalid_account(){
        AccountNumber accountNumber = AccountNumber.createFromAccountNumberText("664371495");
        assertThat(accountNumber.getAccountNumberTextWithStatus(), is("664371495 ERR"));
    }

    @Test
    public void parse_account_with_validation_for_account_with_invalid_characters(){
        AccountNumber accountNumber = AccountNumber.createFromAccountNumberText("86110??36");
        assertThat(accountNumber.getAccountNumberTextWithStatus(), is("86110??36 ILL"));
    }
}