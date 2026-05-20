package day1java;

public class Order {

	private int id;
	
	private String userName;
	
	private String mobileNumber;
	
	private String address;
	
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", userName=" + userName + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", email=" + email + "]";
	}

	public Order(int id, String userName, String mobileNumber, String address, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.email = email;
	}
	
	public Order() {
		
	}
}
