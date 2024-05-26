package org.example;

import java.util.HashMap;

public class Reactor {
    String name;
    String country;
    String status;
    String type;
    String owner;
    double thermalCapacity;
    String firstGridConnection;
    String suspendedDate;
    String permanentShutdownDate;
    double burnup;
    String region;
    HashMap<String, Double> yearLoadFactor;


    // Constructor
    public Reactor(String name, String country, String status, String type, String owner,
                   double thermalCapacity, String firstGridConnection, String suspendedDate,
                   String permanentShutdownDate, double burnup, String region, HashMap<String, Double> yearLoadFactor) {
        this.name = name;
        this.country = country;
        this.status = status;
        this.type = type;
        this.owner = owner;
        this.thermalCapacity = thermalCapacity;
        this.firstGridConnection = firstGridConnection;
        this.suspendedDate = suspendedDate;
        this.permanentShutdownDate = permanentShutdownDate;
        this.burnup = burnup;
        this.region = region;
        this.yearLoadFactor = yearLoadFactor;
    }
    public HashMap<String, Double> getConsumption(){
        HashMap<String, Double> consumption = new HashMap<>();
        for (String year: this.yearLoadFactor.keySet()){
            consumption.put(year,(thermalCapacity / burnup) * (yearLoadFactor.get(year) / 100000)*365);
        }
        return consumption;
    }


    // Getters and Setters
    // ...
}
