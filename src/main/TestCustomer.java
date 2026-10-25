package main;
import model.Customer;

public class TestCustomer{
	public static void main(String[] args) {
		Customer customer=new Customer("C101","Sanjay","9876543210");
		System.out.println(customer);
	}
}