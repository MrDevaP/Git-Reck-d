package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

import java.util.Objects;

/**
 * The type Donation manager.
 */
public class DonationManager {

    /**
     * Check value string.
     *
     * @param value the value
     * @return the string
     */
    public static String checkValue(String value) {
        if (Objects.equals(value, "")) {
            throw new IllegalArgumentException("Donation must contain a value.");
        } else if (value.startsWith("$")) {
            if (!value.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$")) {
                throw new IllegalArgumentException("Value must be the correct format, $x.xx");
            }
        } else {
            if (!value.matches("(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$")) {
                throw new IllegalArgumentException("Value must be in the correct format, $x.xx");
            } else {
                return "$" + value;
            }
        }
        return value;
    }

}
