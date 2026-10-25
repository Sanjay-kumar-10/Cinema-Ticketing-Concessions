package model;

public class Screen{
	private String screenId;
	private String screenName;
	private int totalSeats;
	
	public Screen(String screenId, String screenName, int totalSeats) {
		this.screenId = screenId;
		this.screenName = screenName;
		this.totalSeats = totalSeats;
	}
	
	public String getScreenId() {
		return screenId;
	}
	public String getScreenName() {
		return screenName;
	}
	public int getTotalSeats() {
		return totalSeats;
	}

	public String toString() {
		return "Screen Id="+screenId+"\nScreen Name="+screenName+"\nTotal Seats="+totalSeats;
	}
	
}