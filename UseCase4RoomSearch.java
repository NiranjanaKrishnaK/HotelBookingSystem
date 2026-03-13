import java.util.HashMap;

/**
 * UseCase4RoomSearch
 * ------------------
 * Version 4.1 - Refactored version
 *
 * Demonstrates room search and availability check
 * with read-only access to centralized inventory.
 *
 * Author: Developer
 * Date: March 2026
 */
abstract class Room {
    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public abstract void displayDetails();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
    }
}

// Centralized Inventory
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public HashMap<String, Integer> getInventorySnapshot() {
        return new HashMap<>(inventory); // defensive copy
    }
}

// Search Service (read-only)
class RoomSearchService {
    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void displayAvailableRooms(Room[] rooms) {
        System.out.println("\n=== Available Rooms ===");
        HashMap<String, Integer> snapshot = inventory.getInventorySnapshot();

        for (Room room : rooms) {
            int available = snapshot.getOrDefault(room.roomType, 0);
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App - Room Search ===");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 5);
        inventory.addRoomType("Double Room", 3);
        inventory.addRoomType("Suite Room", 0); // deliberately unavailable

        // Initialize room objects
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Display available rooms (read-only)
        searchService.displayAvailableRooms(rooms);

        System.out.println("=== End of Use Case 4 ===");
    }
}