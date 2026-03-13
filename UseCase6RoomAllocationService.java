import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;
    private int roomsRequested;

    public Reservation(String guestName, String roomType, int roomsRequested) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomsRequested = roomsRequested;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomsRequested() {
        return roomsRequested;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Rooms Requested: " + roomsRequested);
    }
}

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

    public boolean allocateRoom(String roomType, int requested) {
        int available = getAvailability(roomType);
        if (requested <= available) {
            inventory.put(roomType, available - requested);
            return true;
        }
        return false;
    }
}

class BookingService {
    private Queue<Reservation> requestQueue;
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.requestQueue = new LinkedList<>();
        this.allocatedRooms = new HashMap<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Request queued for " + reservation.getGuestName());
    }

    public void processRequests() {
        System.out.println("\n=== Processing Booking Requests ===");
        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.poll();
            r.displayReservation();

            if (inventory.allocateRoom(r.getRoomType(), r.getRoomsRequested())) {
                for (int i = 1; i <= r.getRoomsRequested(); i++) {
                    String roomId = r.getRoomType().replace(" ", "") + "-" + UUID.randomUUID();
                    allocatedRooms.computeIfAbsent(r.getRoomType(), k -> new HashSet<>()).add(roomId);
                    System.out.println("Confirmed: " + r.getGuestName() + " assigned Room ID " + roomId);
                }
            } else {
                System.out.println("Sorry, not enough " + r.getRoomType() + " available for " + r.getGuestName());
            }
        }
    }

    public void displayAllocatedRooms() {
        System.out.println("\n=== Allocated Rooms ===");
        for (String roomType : allocatedRooms.keySet()) {
            System.out.println(roomType + " -> " + allocatedRooms.get(roomType));
        }
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single Room", 3);
        inventory.addRoomType("Double Room", 2);
        inventory.addRoomType("Suite Room", 1);

        BookingService bookingService = new BookingService(inventory);

        System.out.println("=== Book My Stay App - Reservation Confirmation & Allocation ===");

        boolean continueInput = true;
        while (continueInput) {
            System.out.print("\nEnter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single Room/Double Room/Suite Room): ");
            String roomType = scanner.nextLine();

            System.out.print("Enter number of rooms requested: ");
            int roomsRequested = Integer.parseInt(scanner.nextLine());

            Reservation reservation = new Reservation(guestName, roomType, roomsRequested);
            bookingService.addRequest(reservation);

            System.out.print("\nDo you want to add another request? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                continueInput = false;
            }
        }

        bookingService.processRequests();
        bookingService.displayAllocatedRooms();

        System.out.println("=== End of Use Case 6 ===");
        scanner.close();
    }
}