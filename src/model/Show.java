package model;

public class Show{
	private String showId;
	private Movie movie;
	private Screen screen;
	private String showTime;
	private double ticketPrice;
	public Show(String showId, Movie movie, Screen screen, String showTime, double ticketPrice) {
		this.showId = showId;
		this.movie = movie;
		this.screen = screen;
		this.showTime = showTime;
		this.ticketPrice = ticketPrice;
	}
	public String getShowId() {
		return showId;
	}
	public Movie getMovie() {
		return movie;
	}
	public Screen getScreen() {
		return screen;
	}
	public String getShowTime() {
		return showTime;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public String toString() {
		return "Show Id="+showId+"\nMovie="+movie.getTitle()+"\nScreen="+screen.getScreenName()+"\nShow Time="+showTime+"\nTicket Price= Rs."+ticketPrice;
	}
	
}