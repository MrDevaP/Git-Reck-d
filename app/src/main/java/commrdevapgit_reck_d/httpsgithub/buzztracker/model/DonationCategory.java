package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

/**
 * The enum Donation category.
 */
public enum DonationCategory {
    /**
     * Clothing donation category.
     */
    CLOTHING("Clothing"),
    /**
     * Hat donation category.
     */
    HAT("Hat"),
    /**
     * Kitchen donation category.
     */
    KITCHEN("Kitchen"),
    /**
     * Electronics donation category.
     */
    ELECTRONICS("Electronics"),
    /**
     * Household donation category.
     */
    HOUSEHOLD("Household"),
    /**
     * Other donation category.
     */
    OTHER("Other");

    private final String _representation;

    DonationCategory(String representation) {_representation = representation;}

    public String toString() {return _representation;}
}
