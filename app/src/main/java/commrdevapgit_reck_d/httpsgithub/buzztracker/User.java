package commrdevapgit_reck_d.httpsgithub.buzztracker;

public class User {

    private String email;
    private UserType type;


    public User(String email, UserType type) {
        this.email = email;
        this.type = type;
        //put into database
    }

    public String getEmail() {
        return email;
    }

    public UserType getType() {return type;}
}
