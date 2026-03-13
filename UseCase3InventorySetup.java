import java.util.HashMap;
import java.util.Scanner;

/**
 * UseCase3InventorySetup
 * ----------------------
 * Version 3.1 - Refactored version
 *
 * Demonstrates centralized room inventory management
 * using HashMap as a single source of truth.
 *
 * Author: Developer
 * Date: March 2026
 */
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Initialize room availability
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public boolean bookRoom(String roomType, int requested) {
        int available = getAvailability(roomType);
        if (requested <= available) {
            inventory.put(roomType, available - requested);
            return true;
        }
        return false;
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n=== Current Room Inventory ===");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " -> Available: " + inventory.get(roomType));
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Book My Stay App - Centralized Inventory ===");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 2);

        // Display initial inventory
        inventory.displayInventory();

        // User booking input
        System.out.println("\nWhich room type would you like to book?");
        System.out.println("Options: Single Room, Double Room, Suite Room");
        System.out.print("Enter room type: ");
        String choice = scanner.nextLine();

        System.out.print("Enter number of rooms to book: ");
        int requested = scanner.nextInt();

        if (inventory.bookRoom(choice, requested)) {
            System.out.println("Booking confirmed for " + requested + " " + choice + "(s).");
        } else {
            System.out.println("Not enough " + choice + " available.");
        }

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("=== End of Use Case 3 ===");
        scanner.close();
    }
}
