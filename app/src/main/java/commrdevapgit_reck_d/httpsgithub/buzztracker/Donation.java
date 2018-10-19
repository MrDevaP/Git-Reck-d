package commrdevapgit_reck_d.httpsgithub.buzztracker;

import java.text.SimpleDateFormat;

public class Donation {

    private String timestamp;
    private Location host;
    private String shortDescription, fullDescription;
    private DonationCategory category;
    private String value;

    public Donation(String timestamp, Location host, String shortDescription, String fullDescription, DonationCategory category, String value) {
        this.timestamp = timestamp;
        this.host = host;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.category = category;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Location getHost() {
        return host;
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
        return "Location Name: " + host.getName() + "\n" + "Category:" + getCategory() + "\n" +
                "Time Donated: " + getTimestamp() + "\n" + "Short Description: " +
                getShortDescription() + "\n" + "Description: " + getFullDescription() + "\n" + "Value:" +
                getValue();
    }
}
