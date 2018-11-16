package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

/**
 * The type Registration manager.
 */
public class RegistrationManager {

    /**
     * Check password format int.
     *
     * @param password the password
     * @return the int
     */
    public static int checkPasswordFormat(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Passwords must be at least 8 characters long.");
        } else if (!password.matches(".*\\d+.*")) {
            throw new IllegalArgumentException("Password must contain at least 1 number.");
        } else if (password.equals(password.toLowerCase())) {
            throw new IllegalArgumentException("Password must contain at least one capital letter");
        } else {
            return 1;
        }
    }

// --Commented out by Inspection START (11/16/2018 2:16 AM):
//    /**
//     * Check email format int.
//     *
//     * @param email the email
//     * @return the int
//     */
//    public static int checkEmailFormat(CharSequence email) {
//        Matcher emailMatcher = Patterns.EMAIL_ADDRESS.matcher(email);
//        if (Objects.equals(email, "")) {
//            throw new IllegalArgumentException("Email cannot be empty");
//        } else if (!emailMatcher.matches()) {
//            throw new IllegalArgumentException("Email must be a valid email");
//        } else {
//            return 1;
//        }
//    }
// --Commented out by Inspection STOP (11/16/2018 2:16 AM)
}
