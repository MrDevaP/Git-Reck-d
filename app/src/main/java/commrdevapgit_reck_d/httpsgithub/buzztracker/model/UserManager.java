package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

class UserManager {
    public static boolean checkCanAdd(User user) {
        UserType returnTemp = user.getType();
        return returnTemp.equals(UserType.EMPLOYEE);
        //return user.getType().equals(UserType.EMPLOYEE);
    }
}
