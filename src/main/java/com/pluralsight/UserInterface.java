package com.pluralsight;


import java.util.ArrayList;
import java.util.Scanner;

    public class UserInterface {
        private Dealership dealership;
        private FileManager fileManager;
        private Scanner scanner;
        private final String filePath = "src/main/resources/inventory.csv";

        public UserInterface() {
            fileManager = new FileManager();
            scanner = new Scanner(System.in);
            dealership = fileManager.getDealership(filePath);
        }

        public void display() {
            String menu = """
                
                ================================
                  🚗  DEALERSHIP MENU
                ================================
                1️⃣  Show all vehicles
                2️⃣  Search by make & model
                3️⃣  Search by price range
                4️⃣  Search by year range
                5️⃣  Search by color
                6️⃣  Search by type
                7️⃣  Search by mileage
                8️⃣  Add a vehicle
                9️⃣  Remove a vehicle
                0️⃣  Exit
                ================================
                Enter your choice ➤ 
                """;

            int choice;
            do {
                System.out.print(menu);
                choice = scanner.nextInt();
                scanner.nextLine(); // clear input

                switch (choice) {
                    case 1 -> showAllVehicles();
                    case 2 -> searchByMakeModel();
                    case 3 -> searchByPrice();
                    case 4 -> searchByYear();
                    case 5 -> searchByColor();
                    case 6 -> searchByType();
                    case 7 -> searchByMileage();
                    case 8 -> addVehicle();
                    case 9 -> removeVehicle();
                    case 0 -> System.out.println("\nGoodbye! 👋");
                    default -> System.out.println("\n⚠️  Invalid choice, please try again!\n");
                }

            } while (choice != 0);
        }

        private void showAllVehicles() {
            System.out.println("\n🚗 All Vehicles:\n");
            for (Vehicle v : dealership.getAllVehicles()) {
                System.out.println(v);
            }
            System.out.println();
        }

        private void searchByMakeModel() {
            System.out.print("\nEnter make: ");
            String make = scanner.nextLine();
            System.out.print("Enter model: ");
            String model = scanner.nextLine();

            ArrayList<Vehicle> results = dealership.searchByMakeModel(make, model);
            displayResults(results);
        }

        private void searchByPrice() {
            System.out.print("\nEnter min price: ");
            double min = scanner.nextDouble();
            System.out.print("Enter max price: ");
            double max = scanner.nextDouble();

            ArrayList<Vehicle> results = dealership.searchByPrice(min, max);
            displayResults(results);
        }

        private void searchByYear() {
            System.out.print("\nEnter min year: ");
            int min = scanner.nextInt();
            System.out.print("Enter max year: ");
            int max = scanner.nextInt();

            ArrayList<Vehicle> results = dealership.searchByYear(min, max);
            displayResults(results);
        }

        private void searchByColor() {
            System.out.print("\nEnter color: ");
            String color = scanner.nextLine();

            ArrayList<Vehicle> results = dealership.searchByColor(color);
            displayResults(results);
        }

        private void searchByType() {
            System.out.print("\nEnter type: ");
            String type = scanner.nextLine();

            ArrayList<Vehicle> results = dealership.searchByType(type);
            displayResults(results);
        }

        private void searchByMileage() {
            System.out.print("\nEnter min mileage: ");
            int min = scanner.nextInt();
            System.out.print("Enter max mileage: ");
            int max = scanner.nextInt();

            ArrayList<Vehicle> results = dealership.searchByMileage(min, max);
            displayResults(results);
        }

        private void addVehicle() {
            System.out.println("\nAdding a new vehicle...\n");

            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Make: ");
            String make = scanner.nextLine();
            System.out.print("Model: ");
            String model = scanner.nextLine();
            System.out.print("Type: ");
            String type = scanner.nextLine();
            System.out.print("Color: ");
            String color = scanner.nextLine();
            System.out.print("Mileage: ");
            int mileage = scanner.nextInt();
            System.out.print("Price: ");
            double price = scanner.nextDouble();

            Vehicle v = new Vehicle(id, year, make, model, type, color, mileage, price);
            dealership.addVehicle(v);
            fileManager.appendVehicle(v, filePath);

            System.out.println("\n✅ Vehicle added successfully!\n");
        }

        private void removeVehicle() {
            System.out.print("\nEnter vehicle ID to remove: ");
            String id = scanner.nextLine();

            dealership.removeVehicle(id);
            fileManager.saveDealership(dealership, filePath);
            System.out.println("\n❌ Vehicle removed successfully!\n");
        }

        private void displayResults(ArrayList<Vehicle> results) {
            System.out.println("\n🔎 Search Results:\n");
            if (results.isEmpty()) {
                System.out.println("No matching vehicles found.\n");
            } else {
                for (Vehicle v : results) {
                    System.out.println(v);
                }
                System.out.println();
            }
        }
    }
