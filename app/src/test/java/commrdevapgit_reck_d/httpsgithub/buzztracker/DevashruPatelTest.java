package commrdevapgit_reck_d.httpsgithub.buzztracker;

import org.junit.Before;
import org.junit.Test;

import commrdevapgit_reck_d.httpsgithub.buzztracker.model.RegistrationManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DevashruPatelTest {

    private String emptyPassword;
    private String shortPassword;
    private String letterPassword;
    private String lowercasePassword;
    private String acceptablePassword;

    @Before
    public void setUp() {
        emptyPassword = "";
        shortPassword = "Abcdef1";
        letterPassword = "Abcdefgh";
        lowercasePassword = "abcdefg1";
        acceptablePassword = "Abcefgh1";

    }

    @Test
    public void testPasswordFormat() {
        try {
            RegistrationManager.checkPasswordFormat(emptyPassword);
            fail("Empty password was accepted.");
        } catch (IllegalArgumentException e) {}

        try {
            RegistrationManager.checkPasswordFormat(shortPassword);
            fail("Password under 8 characters was accepted.");
        } catch (IllegalArgumentException e) {}

        try {
            RegistrationManager.checkPasswordFormat(letterPassword);
            fail("Password without number was accepted.");
        } catch (IllegalArgumentException e) {}

        try {
            RegistrationManager.checkPasswordFormat(lowercasePassword);
            fail("Password without capital was accepted.");
        } catch (IllegalArgumentException e) {}

        try {
            assertEquals(1, RegistrationManager.checkPasswordFormat(acceptablePassword));
        } catch (IllegalArgumentException e) {
            fail("Acceptable password was rejected.");
        }
    }
}
