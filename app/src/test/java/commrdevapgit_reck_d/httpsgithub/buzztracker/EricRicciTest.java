package commrdevapgit_reck_d.httpsgithub.buzztracker;

import org.junit.Before;
import org.junit.Test;

import commrdevapgit_reck_d.httpsgithub.buzztracker.model.DonationManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EricRicciTest {

    private String emptyValue;
    private String noDollarSign;
    private String smallValue;
    private String smallValueNoZero;
    private String tooManyDecimals;
    private String tooLittleDecimals;
    private String highValue;
    private String noDecimals;
    private String leadingZero;


    @Before
    public void setUp() {
        emptyValue = "";
        noDollarSign = "64.57";
        smallValue = "$0.64";
        smallValueNoZero = "$.64";
        tooManyDecimals = "$64.876";
        noDecimals = "$64";
        tooLittleDecimals = "$3.0";
        highValue = "$1,346,723.89";
        leadingZero = "$03.45";
    }

    @Test
    public void testValidValue() {
        try {
            DonationManager.checkValue(emptyValue);
            fail("Empty value was accepted.");
        } catch (IllegalArgumentException e) {}

        try {
            assertEquals("$64.57", DonationManager.checkValue(noDollarSign));
        } catch (IllegalArgumentException e) {
            fail("Acceptable value was rejected");
        }

        try {
            assertEquals("$0.64", DonationManager.checkValue(smallValue));
        } catch (IllegalArgumentException e) {
            fail("Acceptable value rejected");
        }

        try {
            DonationManager.checkValue(smallValueNoZero);
            fail("Value without leading number accepted");
        } catch (IllegalArgumentException e) {}

        try {
            DonationManager.checkValue(tooManyDecimals);
            fail("Value with too many decimals accepted");
        } catch (IllegalArgumentException e) {}

        try {
            assertEquals("$64", DonationManager.checkValue(noDecimals));
        } catch (IllegalArgumentException e) {
            fail("Acceptable value rejected");
        }

        try {
            DonationManager.checkValue(tooLittleDecimals);
            fail("Value with too little decimals accepted");
        } catch (IllegalArgumentException e) {}

        try {
            assertEquals("$1,346,723.89", DonationManager.checkValue(highValue));
        } catch (IllegalArgumentException e) {
            fail("Acceptable value rejected");
        }

        try {
            DonationManager.checkValue(leadingZero);
            fail("Value with leading zero accepted");
        } catch (IllegalArgumentException e) {}

    }
}
