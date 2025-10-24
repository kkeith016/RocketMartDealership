package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

    public class Dealership {
        private String name;
        private String address;
        private String phone;
        private List<Vehicle> inventory;

        public Dealership(String name, String address, String phone) {
            this.name = name;
            this.address = address;
            this.phone = phone;
            this.inventory = new ArrayList<>();
        }
        public String getName() { return name; }
        public String getAddress() { return address; }
        public String getPhone() { return phone; }
        public List<Vehicle> getInventory() { return inventory; }

        public void addVehicle(Vehicle v) {
            inventory.add(v);
        }

}
