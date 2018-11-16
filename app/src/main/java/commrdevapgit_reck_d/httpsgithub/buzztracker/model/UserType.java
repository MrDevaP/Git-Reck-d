package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

/**
 * The enum User type.
 */
public enum UserType {
    /**
     * General user type.
     */
    GENERAL("General"),
    /**
     * Employee user type.
     */
    EMPLOYEE("Employee"),
    /**
     * Manager user type.
     */
    MANAGER("Manager"),
    /**
     * Admin user type.
     */
    ADMIN("Admin");

    private final String _representation;

    UserType(String representation) {_representation = representation;}

    public String toString() {return _representation;}
}
