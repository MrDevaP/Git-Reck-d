package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

public class User {

    private String email;
    private UserType type;


    public User(String email, UserType type) {
        this.email = email;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public UserType getType() {return type;}
}
