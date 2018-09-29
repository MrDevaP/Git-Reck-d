package commrdevapgit_reck_d.httpsgithub.buzztracker;

import java.util.HashMap;

public class User {

    private String username, password;
    private static HashMap<String, User> users = new HashMap<>();


    public User(String username, String password) {
        if (username.equals("") || password.equals("")) {
            throw new IllegalArgumentException("Please enter a username and password to register.");
        } else if (users.containsKey(username)) {
            throw new IllegalArgumentException("Sorry that username is taken. Please select another.");
        }
        this.username = username;
        this.password = password;
        users.put(username, this);
    }

    public static HashMap getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
