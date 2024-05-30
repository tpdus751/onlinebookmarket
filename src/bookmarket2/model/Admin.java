package bookmarket2.model;

public class Admin extends User {

	private String id = "admin";
	private String password = "0605";
	
	public Admin(String name, String phone) {
		super(name, phone);
	}

	public boolean login(String id, String password) {
		if (this.id.equals(id) && this.password.equals(password))
			return true;
		return false;
	}

	
	
}

