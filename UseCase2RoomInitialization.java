import java.util.Scanner;

/**
 * UseCase2RoomInitialization
 * --------------------------
 * Version 2.1 - Refactored version with user input
 *
 * Demonstrates basic room types, static availability,
 * and allows user to request a booking.
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

// Concrete room classes
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

public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Book My Stay App - Room Types ===");

        // Static availability variables
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display details
        single.displayDetails();
        System.out.println("Available: " + singleRoomAvailability);

        doubleR.displayDetails();
        System.out.println("Available: " + doubleRoomAvailability);

        suite.displayDetails();
        System.out.println("Available: " + suiteRoomAvailability);

        // User input for booking
        System.out.println("\nWhich room type would you like to book?");
        System.out.println("1. Single Room");
        System.out.println("2. Double Room");
        System.out.println("3. Suite Room");
        System.out.print("Enter choice (1-3): ");
        int choice = scanner.nextInt();

        System.out.print("Enter number of rooms to book: ");
        int roomsRequested = scanner.nextInt();

        switch (choice) {
            case 1:
                if (roomsRequested <= singleRoomAvailability) {
                    singleRoomAvailability -= roomsRequested;
                    System.out.println("Booking confirmed for " + roomsRequested + " Single Room(s).");
                } else {
                    System.out.println("Not enough Single Rooms available.");
                }
                break;
            case 2:
                if (roomsRequested <= doubleRoomAvailability) {
                    doubleRoomAvailability -= roomsRequested;
                    System.out.println("Booking confirmed for " + roomsRequested + " Double Room(s).");
                } else {
                    System.out.println("Not enough Double Rooms available.");
                }
                break;
            case 3:
                if (roomsRequested <= suiteRoomAvailability) {
                    suiteRoomAvailability -= roomsRequested;
                    System.out.println("Booking confirmed for " + roomsRequested + " Suite Room(s).");
                } else {
                    System.out.println("Not enough Suite Rooms available.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("\nUpdated Availability:");
        System.out.println("Single Rooms: " + singleRoomAvailability);
        System.out.println("Double Rooms: " + doubleRoomAvailability);
        System.out.println("Suite Rooms: " + suiteRoomAvailability);

        System.out.println("=== End of Use Case 2 ===");
        scanner.close();
    }
}