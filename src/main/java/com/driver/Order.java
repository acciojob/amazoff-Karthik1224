package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM


        // Split the string by ":" to separate hours and minutes
        String[] timeParts = deliveryTime.split(":");
        int hours = Integer.parseInt(timeParts[0]); // Convert hours to an integer
        int minutes = Integer.parseInt(timeParts[1]); // Convert minutes to an integer

        this.id = id;
        this.deliveryTime =hours * 60 + minutes;
    }

    public String getId()
    {
        return id;
    }

    public int getDeliveryTime()
    {
        return deliveryTime;
    }
}
