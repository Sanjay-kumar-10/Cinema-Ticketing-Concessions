package model;

public class Customer{
	private String customerId;
	private String customerName;
	private String phoneNo;
	public Customer(String customerId, String customerName, String phoneNo) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String toString() {
		return "Customer Id="+customerId+"\nCustomer Name="+customerName+"\nPhone Number="+phoneNo;
	}
}