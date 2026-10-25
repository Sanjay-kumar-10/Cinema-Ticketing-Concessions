package main;

import model.Screen;
import model.Movie;
import model.Show;

public class TestShow{
	public static void main(String[] args) {
		Movie movie=new Movie("M101","Leo",164);
		Screen screen=new Screen("Sc01","Screen 1",50);
		Show show=new Show("Sh01",movie,screen,"10:00 AM",150.0);
		System.out.println(show);
	}
}