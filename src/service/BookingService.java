
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;
public class BookingService {
    private Map<String, Set<String>> bookedSeats = new HashMap<>();
    private Set<Booking> finalizedBookings = new HashSet<>();
    public boolean areSeatsAvailable(String showId, Set<String> requestedSeats) {
        Set<String> alreadyBooked = bookedSeats.get(showId);
        if (alreadyBooked == null) {
            return true;
        }
        for (String seatId : requestedSeats) {
            if (alreadyBooked.contains(seatId)) {
                return false;
            }
        }
        return true;
    }
    public Booking createBooking(String bookingId, Customer customer,Show show, List<Seat> requestedSeats) {
        if (bookingId == null || bookingId.trim().isEmpty()
                || customer == null || show == null
                || requestedSeats == null || requestedSeats.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking details.");
        }
        Set<String> seatIds = new HashSet<>();
        for (Seat seat : requestedSeats) {
            if (seat == null || seat.getSeatId() == null
                    || !seatIds.add(seat.getSeatId())) {
                throw new IllegalArgumentException(
                        "Invalid or duplicate seat selected.");
            }
        }
        if (!areSeatsAvailable(show.getShowId(), seatIds)) {
            throw new IllegalStateException("Some seats are already booked.");
        }
        return new Booking(bookingId, customer, show,
                new ArrayList<>(requestedSeats));
    }
    public void confirmPaymentAndFinalize(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null.");
        }
        if (finalizedBookings.contains(booking)) {
            throw new IllegalStateException("Booking already finalized.");
        }
        Show show = booking.getShow();
        Set<String> seatIds = new HashSet<>();

        for (Seat seat : booking.getSeats()) {
            seatIds.add(seat.getSeatId());
        }
        if (!areSeatsAvailable(show.getShowId(), seatIds)) {
            throw new IllegalStateException("Seats are no longer available.");
        }
        booking.confirmPayment();
        bookedSeats.putIfAbsent(show.getShowId(), new HashSet<>());
        bookedSeats.get(show.getShowId()).addAll(seatIds);
        finalizedBookings.add(booking);
    }
    public void cancelBooking(Booking booking) {
        if (booking == null || !finalizedBookings.contains(booking)) {
            throw new IllegalStateException(
                    "Booking is invalid or not finalized.");
        }
        String showId = booking.getShow().getShowId();
        Set<String> seats = bookedSeats.get(showId);
        for (Seat seat : booking.getSeats()) {
            seats.remove(seat.getSeatId());
        }
        booking.cancelBooking();
        finalizedBookings.remove(booking);
        if (seats.isEmpty()) {
            bookedSeats.remove(showId);
        }
    }
    public void displayBookedSeats(String showId) {
        Set<String> seats = bookedSeats.get(showId);
        System.out.println("Show ID: " + showId);
        if (seats == null || seats.isEmpty()) {
            System.out.println("No seats booked.");
        } else {
            System.out.println("Booked seats: " + seats);
        }
    }
}
