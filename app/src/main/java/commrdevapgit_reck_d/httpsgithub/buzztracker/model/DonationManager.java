package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

public class DonationManager {

    public static String checkValue(String value) {
        if (value == "") {
            throw new IllegalArgumentException("Donation must contain a value.");
        } else if (value.startsWith("$")) {
            if (!value.matches("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$")) {
                throw new IllegalArgumentException("Value must be the correct format, $x.xx");
            }
        } else {
            if (!value.matches("(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$")) {
                throw new IllegalArgumentException("Value must be in the correct format, $x.xx");
            }
        }
        return value;
    }

    public static int checkDate(String date) {
        if (date == "") {
            throw new IllegalArgumentException("Date must not be empty");
        } else if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new IllegalArgumentException("Date must be in the correct format, mm/dd/yyyy");
        }
        return 1;
    }
}
