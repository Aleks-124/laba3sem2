package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Calculation {
    ArrayList<Reactor> reactors;

    public Calculation(ArrayList<Reactor> reactors){
        this.reactors = reactors;
    }
    public HashMap<String,HashMap<String,Double>> getValuePerRegion(){
        HashMap<String,HashMap<String,Double>> regionsPerYear = new HashMap<>();
        for (Reactor reactor : reactors) {
            // Получить регион реактора
            String region = reactor.region;

            // Проверить, есть ли такой регион уже в хэш-карте
            if (regionsPerYear.containsKey(region)) {
                // Если есть, то добавить значения потребления за годы
                HashMap<String, Double> consumption = reactor.getConsumption();
                for (String year : consumption.keySet()) {
                    if (regionsPerYear.get(region).containsKey(year)) {
                        regionsPerYear.get(region).put(year, regionsPerYear.get(region).get(year) + consumption.get(year));
                    } else {
                        regionsPerYear.get(region).put(year, consumption.get(year));
                    }
                }
            } else {
                // Если нет, то добавить регион и значения потребления за годы
                regionsPerYear.put(region, reactor.getConsumption());
            }
        }
        return regionsPerYear;
    }
    public HashMap<String,HashMap<String,Double>> getValuePerCountry(){
        HashMap<String,HashMap<String,Double>> countryPerYear = new HashMap<>();
        for (Reactor reactor : reactors) {
            // Получить регион реактора
            String country = reactor.country;

            // Проверить, есть ли такой регион уже в хэш-карте
            if (countryPerYear.containsKey(country)) {
                // Если есть, то добавить значения потребления за годы
                HashMap<String, Double> consumption = reactor.getConsumption();
                for (String year : consumption.keySet()) {
                    if (countryPerYear.get(country).containsKey(year)) {
                        countryPerYear.get(country).put(year, countryPerYear.get(country).get(year) + consumption.get(year));
                    } else {
                        countryPerYear.get(country).put(year, consumption.get(year));
                    }
                }
            } else {
                // Если нет, то добавить регион и значения потребления за годы
                countryPerYear.put(country, reactor.getConsumption());
            }
        }
        return countryPerYear;
    }

    public HashMap<String,HashMap<String,Double>> getValuePerOwner(){
        HashMap<String,HashMap<String,Double>> ownerPerYear = new HashMap<>();
        for (Reactor reactor : reactors) {
            // Получить регион реактора
            String owner = reactor.owner;

            // Проверить, есть ли такой регион уже в хэш-карте
            if (ownerPerYear.containsKey(owner)) {
                // Если есть, то добавить значения потребления за годы
                HashMap<String, Double> consumption = reactor.getConsumption();
                for (String year : consumption.keySet()) {
                    if (ownerPerYear.get(owner).containsKey(year)) {
                        ownerPerYear.get(owner).put(year, ownerPerYear.get(owner).get(year) + consumption.get(year));
                    } else {
                        ownerPerYear.get(owner).put(year, consumption.get(year));
                    }
                }
            } else {
                // Если нет, то добавить регион и значения потребления за годы
                ownerPerYear.put(owner, reactor.getConsumption());
            }
        }
        return ownerPerYear;
    }

}
