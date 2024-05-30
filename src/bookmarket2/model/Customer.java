package bookmarket2.model;

public class Customer extends User {
	private String address;
	private String email;
	
	public Customer(String name, String phone, String address, String email) {
		super(name, phone);
		this.address = address;
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
