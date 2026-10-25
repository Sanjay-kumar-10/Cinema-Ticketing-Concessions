package main;

import model.ConcessionOrder;

public class TestConcessionOrder{
	public static void main(String[] args) {
		ConcessionOrder order=new ConcessionOrder("CO101",null);
		order.addItem("Popcorn",2, 120.0);
		order.addItem("Coke", 1, 80.0);
		
		System.out.println(order);
	}
}