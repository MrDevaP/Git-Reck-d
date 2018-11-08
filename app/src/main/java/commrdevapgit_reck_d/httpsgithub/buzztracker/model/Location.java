package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

/**
 * The type Location.
 */
public class Location {

    private String name, type, address, phoneNumber;
    private float latitude, longitude;

    /**
     * Instantiates a new Location.
     */
    public Location() {}

    /**
     * Instantiates a new Location.
     *
     * @param name        the name
     * @param type        the type
     * @param address     the address
     * @param phoneNumber the phone number
     * @param latitude    the latitude
     * @param longitude   the longitude
     */
    public Location(String name, String type, String address, String phoneNumber, float latitude, float longitude) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" + "Latitude: " + getLatitude() + "\n"
            + "Longitude: " + getLongitude() + "\n" + "Address: " + getAddress() + "\n"
            + "Type: " + getType() + "\n" + "Phone Number: " + getPhoneNumber();
    }
}