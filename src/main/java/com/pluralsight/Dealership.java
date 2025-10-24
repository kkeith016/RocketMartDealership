package com.pluralsight;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<Vehicle>();
    }

    public void addVehicle(Vehicle v) {
        inventory.add(v);
    }

    public boolean removeVehicleById(String id) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getId().equals(id)) {
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public ArrayList<Vehicle> findByMake(String make) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make)) {
                results.add(v);
            }
        }
        return results;
    }

    public ArrayList<Vehicle> findByPriceRange(double min, double max) {
        ArrayList<Vehicle> results = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                results.add(v);
            }
        }
        return results;
    }

    // You can add more search methods similarly (year, type, color, mileage)
}
