package commrdevapgit_reck_d.httpsgithub.buzztracker;

import java.util.HashMap;

public class Location {

    private String name, type, address, phoneNumber;
    private float latitude, longitude;
    private static HashMap<String, Location> locations = new HashMap<>();


    public Location(String name, String type, String address, String phoneNumber, float latitude, float longitude) {
        if (locations.containsKey(name)) {
            return;
        }
        this.name = name;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        locations.put(name, this);
    }

    public static HashMap getLocations() {
        return locations;
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