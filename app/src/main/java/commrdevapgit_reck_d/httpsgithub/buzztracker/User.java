package commrdevapgit_reck_d.httpsgithub.buzztracker;

public class User {

    private String email, password;
    private UserType type;


    public User(String email, String password, UserType type) {
        this.email = email;
        this.password = password;
        this.type = type;
        //put into database
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {return type;}
}
