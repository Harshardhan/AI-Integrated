package miniproject;

public class User {

	private int id;
	
	private String userName;
	
	private String mobileNumber;
	
	private String address;

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

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ "]";
	}

	public User(int id, String userName, String mobileNumber, String address) {
		super();
		this.id = id;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	
	
}
