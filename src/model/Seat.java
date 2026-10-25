package model;

public class Seat{
	private String seatId;
	private boolean available;
	
	public Seat(String seatId, boolean available) {
		this.seatId = seatId;
		this.available = available;
	}
	public String getSeatId() {
		return seatId;
	}
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String toString() {
		return "Seat Id="+seatId+"\nAvailability status="+available;
	}
	
}