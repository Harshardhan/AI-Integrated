package mayrevision;

import java.math.BigDecimal;

public class Order {

	private Long id;
	
	private String customerName;
	
	private String email;
	
	private String mobileNumber;
	
	private String description;
	
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerName=" + customerName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", description=" + description + ", amount=" + amount + "]";
	}

	public Order(Long id, String customerName, String email, String mobileNumber, String description,
			BigDecimal amount) {
		this.id = id;
		this.customerName = customerName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.description = description;
		this.amount = amount;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}


}
