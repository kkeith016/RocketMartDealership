package com.pluralsight;

import java.io.*;

public class FileManager {

    public Dealership getDealership(String filePath) {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // First line: dealership info
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                dealership = new Dealership(parts[0], parts[1], parts[2]);
            }

            // Next lines: vehicle info
            String line2;
            while ((line2 = reader.readLine()) != null) {
                String[] parts = line2.split("\\|");

                String id = parts[0];
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int mileage = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle v = new Vehicle(id, year, make, model, type, color, mileage, price);
                dealership.addVehicle(v);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership d, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // First line: dealership info
            writer.write(d.getName() + "|" + d.getAddress() + "|" + d.getPhone());
            writer.newLine();

            // Next lines: each vehicle
            for (Vehicle v : d.getAllVehicles()) {
                writer.write(v.getId() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel()
                        + "|" + v.getType() + "|" + v.getColor() + "|" + v.getMileage() + "|" + v.getPrice());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Optional: append method (adds vehicles without overwriting)
    public void appendVehicle(Vehicle v, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(v.getId() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel()
                    + "|" + v.getType() + "|" + v.getColor() + "|" + v.getMileage() + "|" + v.getPrice());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending vehicle: " + e.getMessage());
        }
    }
}
