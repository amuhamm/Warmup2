package com.ezmoizy.abdulmoiz.warmup2;

/*
 * This class is to represent each Location object.
 * Currently only represents buildings based on the BUILDINGS array.
 */
public class Location {

    private static String name;
    private static long lat;
    private static long lng;
    private static String description;
    private static String[] rooms;
    private static String[] building_level;

    public Location(String name, long latitude, long longitude) {
        this.name = name;
        this.lat = latitude;
        this.lng = longitude;
    }

    public Location(String name, long latitude, long longitude, String room, String level) {
        this.name = name;
        this.lat = latitude;
        this.lng = longitude;
        //to include room and building level data in future releases
    }

    public static void setLat(long lat) {
        Location.lat = lat;
    }

    public static void setLng(long lng) {
        Location.lng = lng;
    }

    public static void setName(String name) {
        Location.name = name;
    }

    public static long getLat() {
        return lat;
    }

    public static long getLng() {
        return lng;
    }

    public static String getName() {
        return name;
    }

    public static String getDescription() {
        return description;
    }

    public static String[] getBuilding_level() {
        return building_level;
    }

    public static String[] getRooms() {
        return rooms;
    }
}
