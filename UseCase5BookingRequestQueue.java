import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * UseCase5BookingRequestQueue
 * ---------------------------
 * Version 5.1 - Refactored version
 *
 * Demonstrates booking request intake using a Queue
 * to preserve arrival order (FIFO principle).
 *
 * Author: Developer
 * Date: March 2026
 */
class Reservation {
    private String guestName;
    private String roomType;
    private int roomsRequested;

    public Reservation(String guestName, String roomType, int roomsRequested) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomsRequested = roomsRequested;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Rooms Requested: " + roomsRequested);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added to queue for " + reservation);
    }

    // Display all queued requests
    public void displayQueue() {
        System.out.println("\n=== Current Booking Request Queue ===");
        if (requestQueue.isEmpty()) {
            System.out.println("No requests in queue.");
        } else {
            for (Reservation r : requestQueue) {
                r.displayReservation();
            }
        }
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingRequestQueue queue = new BookingRequestQueue();

        System.out.println("=== Book My Stay App - Booking Request Queue ===");

        boolean continueInput = true;
        while (continueInput) {
            System.out.print("\nEnter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            System.out.print("Enter number of rooms requested: ");
            int roomsRequested = Integer.parseInt(scanner.nextLine());

            Reservation reservation = new Reservation(guestName, roomType, roomsRequested);
            queue.addRequest(reservation);

            System.out.print("\nDo you want to add another request? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                continueInput = false;
            }
        }

        // Display all requests in queue
        queue.displayQueue();

        System.out.println("=== End of Use Case 5 ===");
        scanner.close();
    }
}