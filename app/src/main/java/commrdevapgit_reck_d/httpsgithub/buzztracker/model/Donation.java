package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

/**
 * The type Donation.
 */
public class Donation {

    private String timestamp;
    private String shortDescription;
    private String fullDescription;
    private String value;
    private DonationCategory category;

    /**
     * Instantiates a new Donation.
     */
    public Donation() {}

    /**
     * Instantiates a new Donation.
     *
     * @param timestamp        the timestamp
     * @param shortDescription the short description
     * @param fullDescription  the full description
     * @param category         the category
     * @param value            the value
     */
    public Donation(String timestamp, String shortDescription, String fullDescription,
                    DonationCategory category, String value) {
        this.timestamp = timestamp;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.category = category;
        this.value = value;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Gets full description.
     *
     * @return the full description
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public DonationCategory getCategory() {
        return category;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() { return value; }

    /**
     * converts donation to string
     * @return String value of donation
     */
    public String toString() {
        return "Category: " + getCategory() + "\n" + "Time Donated: " + getTimestamp() + "\n"
                + "Short Description: " + getShortDescription() + "\n" + "Description: "
                + getFullDescription() + "\n" + "Value: " + getValue();
    }
}
