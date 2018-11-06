package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

public enum UserType {
    GENERAL("General"),
    EMPLOYEE("Employee"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private String _representation = "";

    UserType(String representation) {_representation = representation;}

    public String toString() {return _representation;}
}
