package model;

import java.util.LinkedHashMap;
import java.util.Map;


public class ConcessionOrder{
	private String orderId;
	private Booking booking;
	private Map<String,Integer> items;
	private Map<String,Double> prices;
	
	public ConcessionOrder(String orderId, Booking booking) {
		this.orderId = orderId;
		this.booking = booking;
		this.items = new LinkedHashMap<>();
		this.prices = new LinkedHashMap<>();
	}
	public String getOrderId() {
		return orderId;
	}
	public Booking getBooking() {
		return booking;
	}
	public void addItem(String item,int quantity,double price) {
		if(quantity<=0||price<0) {
			throw new IllegalArgumentException("Quantity and price cannot be negative.");
		}
		items.put(item,items.getOrDefault(item, 0)+quantity);
		prices.put(item, price);
	}
	public double calculateTotal() {
		double total=0;
		for(String item:items.keySet()) {
			total+=items.get(item)*prices.get(item);
		}
		return total;
	}
	
	public String toString() {
		StringBuilder receipt=new StringBuilder();
		receipt.append("Order Id: ").append(orderId).append("\n");
		
		for(String item:items.keySet()) {
			int quantity=items.get(item);
			double price=prices.get(item);
			
			receipt.append(item).append(" | Quantity: ").append(quantity)
					.append(" | Price: ").append(price)
					.append(" | Subtotal: ").append(quantity*price)
					.append("\n");
		}
		receipt.append("Total: Rs.").append(calculateTotal());
		return receipt.toString();
	}
}