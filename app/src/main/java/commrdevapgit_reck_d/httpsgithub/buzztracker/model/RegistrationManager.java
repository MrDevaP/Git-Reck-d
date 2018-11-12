package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

import android.util.Patterns;

public class RegistrationManager {

    public static int checkPasswordFormat(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Passwords must be at least 8 characters long.");
        } else if (!password.matches(".*\\d+.*")) {
            throw new IllegalArgumentException("Password must contain at least 1 number.");
        } else if (password.equals(password.toLowerCase())) {
            throw new IllegalArgumentException("Password must contain at least one captial letter");
        } else {
            return 1;
        }
    }

    public static int checkEmailFormat(String email) {
        if (email == "") {
            throw new IllegalArgumentException("Email cannot be empty");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw new IllegalArgumentException("Email must be a valid email");
        } else {
            return 1;
        }
    }
}
