package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

public class UserManager {
    public static boolean checkCanAdd(User user) {
        return user.getType().equals(UserType.EMPLOYEE);
    }
}
