package main;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class TestBooking{
	public static void main(String[] args) {
		Movie movie=new Movie("M101","Leo",164);
		Screen screen=new Screen("Sc01","Screen 1",50);
		Show show=new Show("Sh01",movie,screen,"10:00 AM",150.0);
		Customer customer=new Customer("C101","Sanjay","9876543210");
		Seat seat1=new Seat("A1",false);
		Seat seat2=new Seat("A2",false);
		
		List<Seat> seats=new ArrayList<>();
		seats.add(seat1);
		seats.add(seat2);
		
		Booking booking=new Booking("B101",customer,show,seats);
		System.out.println(booking);
		System.out.println("After Payment Confirmation:");
		booking.confirmPayment();
		System.out.println("Payment Confirmed="+booking.isPaymentConfirmed());
	}
}