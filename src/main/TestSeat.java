package main;
import model.Seat;
public class TestSeat{
	public static void main(String[] args) {
		Seat seat=new Seat("A1",true);
		System.out.println(seat);
		System.out.println("\nAfter booking:");
		seat.setAvailable(false);
		System.out.println(seat);
		
	}
}