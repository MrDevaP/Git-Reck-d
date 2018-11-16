package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

/**
 * The type User.
 */
public class User {

    private String email;
    private UserType type;


    /**
     * Instantiates a new User.
     *
     * @param email the email
     * @param type  the type
     */
    public User(String email, UserType type) {
        this.email = email;
        this.type = type;
    }

// --Commented out by Inspection START (11/16/2018 3:55 AM):
//    /**
//     * Gets type.
//     *
//     * @return the type
//     */
//    public UserType getType() {return type;}
// --Commented out by Inspection STOP (11/16/2018 3:55 AM)
}
