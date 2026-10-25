
package main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Booking;
import model.ConcessionOrder;
import model.Customer;
import model.Movie;
import model.Screen;
import model.Seat;
import model.Show;
import service.BookingService;
import service.ConcessionService;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static Map<String, Show> shows = new LinkedHashMap<>();
    private static Map<String, Booking> bookings = new LinkedHashMap<>();

    private static BookingService bookingService = new BookingService();
    private static ConcessionService concessionService =
            new ConcessionService();

    private static int bookingCount = 1;
    private static int orderCount = 1;

    public static void main(String[] args) {

        Movie movie1 = new Movie("M101", "Leo", 160);
        Movie movie2 = new Movie("M102", "Jailer", 165);

        Screen screen1 = new Screen("SC101", "Screen 1", 50);
        Screen screen2 = new Screen("SC102", "Screen 2", 50);

        shows.put("SH101", new Show(
                "SH101", movie1, screen1, "10:00 AM", 150.0));

        shows.put("SH102", new Show(
                "SH102", movie2, screen2, "02:00 PM", 180.0));

        int choice;

        do {
            System.out.println("\n===== CINEMA TICKETING SYSTEM =====");
            System.out.println("1. Display Shows");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Order Concessions");
            System.out.println("5. Display Booked Seats");
            System.out.println("6. Display Booking Receipt");
            System.out.println("7. Display Concession Orders");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            choice = readInt();

            try {
                switch (choice) {
                    case 1:
                        displayShows();
                        break;

                    case 2:
                        bookTickets();
                        break;

                    case 3:
                        cancelBooking();
                        break;

                    case 4:
                        orderConcessions();
                        break;

                    case 5:
                        displayBookedSeats();
                        break;

                    case 6:
                        displayBookingReceipt();
                        break;

                    case 7:
                        concessionService.displayOrders();
                        break;

                    case 8:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 8);

        sc.close();
    }

    private static void displayShows() {

        System.out.println("\nAvailable Shows:");

        for (Show show : shows.values()) {
            System.out.println(show);
        }
    }

    private static void bookTickets() {

        displayShows();

        System.out.print("Enter Show ID: ");
        String showId = sc.nextLine().trim();

        Show show = shows.get(showId);

        if (show == null) {
            System.out.println("Show not found.");
            return;
        }

        System.out.print("Customer ID: ");
        String customerId = sc.nextLine().trim();

        System.out.print("Customer Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Phone Number: ");
        String phone = sc.nextLine().trim();

        Customer customer = new Customer(customerId, name, phone);

        System.out.print("Enter seat IDs separated by commas (e.g. A1,A2): ");
        String input = sc.nextLine();

        String[] seatNames = input.split(",");
        List<Seat> seats = new ArrayList<>();

        for (String seatName : seatNames) {
            String seatId = seatName.trim();

            if (seatId.isEmpty()) {
                throw new IllegalArgumentException("Invalid seat ID.");
            }

            seats.add(new Seat(seatId, true));
        }

        String bookingId = "BK" + bookingCount;

        Booking booking = bookingService.createBooking(
                bookingId, customer, show, seats);

        System.out.println("Booking ID: " + bookingId);
        System.out.println("Ticket Fare: Rs. " + booking.calculateFare());

        System.out.print("Confirm payment and finalize booking? (yes/no): ");
        String payment = sc.nextLine().trim();

        if (payment.equalsIgnoreCase("yes")) {
            bookingService.confirmPaymentAndFinalize(booking);
            bookings.put(bookingId, booking);
            bookingCount++;

            System.out.println("Payment confirmed!");
            System.out.println("Booking completed successfully.");
            System.out.println(booking);
        } else {
            System.out.println("Payment not confirmed. Booking not finalized.");
        }
    }

    private static void cancelBooking() {

        System.out.print("Enter Booking ID: ");
        String bookingId = sc.nextLine().trim();

        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        bookingService.cancelBooking(booking);
        bookings.remove(bookingId);

        System.out.println("Booking cancelled. Seats released.");
    }

    private static void orderConcessions() {

        System.out.print("Enter Booking ID: ");
        String bookingId = sc.nextLine().trim();

        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        String orderId = "CO" + orderCount;

        ConcessionOrder order =
                concessionService.createOrder(orderId, booking);

        System.out.println("1. Popcorn - Rs. 120");
        System.out.println("2. Coke - Rs. 80");
        System.out.println("3. Nachos - Rs. 150");
        System.out.println("4. Finish order");

        while (true) {
            System.out.print("Select item: ");
            int itemChoice = readInt();

            if (itemChoice == 4) {
                break;
            }

            String itemName;
            double price;

            switch (itemChoice) {
                case 1:
                    itemName = "Popcorn";
                    price = 120;
                    break;

                case 2:
                    itemName = "Coke";
                    price = 80;
                    break;

                case 3:
                    itemName = "Nachos";
                    price = 150;
                    break;

                default:
                    System.out.println("Invalid item.");
                    continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = readInt();

            order.addItem(itemName, quantity, price);
            System.out.println("Item added.");
        }

        if (order.calculateTotal() == 0) {
            System.out.println("No items ordered.");
            return;
        }

        orderCount++;
        System.out.println("\n===== CONCESSION RECEIPT =====");
        System.out.println(order);
    }

    private static void displayBookedSeats() {

        System.out.print("Enter Show ID: ");
        String showId = sc.nextLine().trim();

        if (!shows.containsKey(showId)) {
            System.out.println("Show not found.");
            return;
        }

        bookingService.displayBookedSeats(showId);
    }

    private static void displayBookingReceipt() {

        System.out.print("Enter Booking ID: ");
        String bookingId = sc.nextLine().trim();

        Booking booking = bookings.get(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
        } else {
            System.out.println("\n===== BOOKING RECEIPT =====");
            System.out.println(booking);
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
