package com.ezmoizy.abdulmoiz.warmup2;

/**
 * Created by Moiz on 25/03/2016.
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



}
