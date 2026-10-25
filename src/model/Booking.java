package model;
import java.util.ArrayList;
import java.util.List;

public class Booking{
	private String bookingId;
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean paymentConfirmed;
    private boolean cancelled;
    
	public Booking(String bookingId, Customer customer, Show show, List<Seat> seats) {
		this.bookingId = bookingId;
		this.customer = customer;
		this.show = show;
		this.seats = new ArrayList<>(seats);
		this.paymentConfirmed = false;
		this.cancelled = false;
	}
	public String getBookingId() {
		return bookingId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Show getShow() {
		return show;
	}
	public List<Seat> getSeats() {
		return new ArrayList<>(seats);
	}
	public boolean isPaymentConfirmed() {
		return paymentConfirmed;
	}
	public boolean isCancelled() {
		return cancelled;
	}
    public void confirmPayment() {
    	paymentConfirmed=true;
    }
    public void cancelBooking() {
    	cancelled=true;
    	for(Seat seat:seats) {
    		seat.setAvailable(true);
    	}
    }
    public double calculateFare() {
    	return seats.size()*show.getTicketPrice();
    }
    public String toString() {
    	return "Booking Id="+bookingId+"\nCustomer="+customer.getCustomerName()+"\nMovie="+show.getMovie().getTitle()
    			+"\nShow Time="+show.getShowTime()+"\nSeats="+seats+"\nFare=Rs."+calculateFare()+"\nPayment Confirmed="+paymentConfirmed
    			+"\nCancelled="+cancelled;
    }
}