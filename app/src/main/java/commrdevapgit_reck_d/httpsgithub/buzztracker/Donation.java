package commrdevapgit_reck_d.httpsgithub.buzztracker;

public class Donation {

    private String timestamp;
    private String shortDescription, fullDescription;
    private String value;
    private DonationCategory category;

    public Donation(String timestamp, String shortDescription, String fullDescription, DonationCategory category, String value) {
        this.timestamp = timestamp;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.category = category;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public DonationCategory getCategory() {
        return category;
    }

    public String getValue() { return value; }

    public String toString() {
        return "Category:" + getCategory() + "\n" +
                "Time Donated: " + getTimestamp() + "\n" + "Short Description: " +
                getShortDescription() + "\n" + "Description: " + getFullDescription() + "\n" + "Value:" +
                getValue();
    }
}
