package commrdevapgit_reck_d.httpsgithub.buzztracker.model;

public class Location {

    private String name, type, address, phoneNumber;
    private float latitude, longitude;

    public Location() {}

    public Location(String name, String type, String address, String phoneNumber, float latitude, float longitude) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getLatitude() {
        return latitude;
    }

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