package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

public enum DonationCategory {
    CLOTHING("Clothing"),
    HAT("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private String _representation = "";

    DonationCategory(String representation) {_representation = representation;}

    public String toString() {return _representation;}
}
