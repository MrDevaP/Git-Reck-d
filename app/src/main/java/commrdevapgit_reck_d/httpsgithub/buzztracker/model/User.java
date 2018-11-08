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

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public UserType getType() {return type;}
}
