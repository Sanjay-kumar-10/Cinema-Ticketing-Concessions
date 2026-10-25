package model;

public class Movie{
	private String movieId;
	private String title;
	private int duration;
	public Movie(String movieId,String title,int duration) {
		this.movieId=movieId;
		this.title=title;
		this.duration=duration;
	}
	public String getMovieId() {
		return movieId;
	}
	public String getTitle() {
		return title;
	}
	public int getDuration() {
		return duration;
	}
	public String toString() {
		return "Movie Id= "+movieId+"\nMovie Name= "+title+"\nDuration(in min)= "+duration;
	}
}