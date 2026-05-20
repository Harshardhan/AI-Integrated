package revision;

import java.math.BigDecimal;

public class Bank {

	private Long id;
	
	private String accountHolder;
	
	private String accountNumber;
	
	private BigDecimal balance;
	
	private String ifscCode;
	
	private String bankName;
	
	private String email;
	
	private String mobileNumber;
	
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal amt) {
		this.balance = amt;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", accountHolder=" + accountHolder + ", accountNumber=" + accountNumber + ", balance="
				+ balance + ", ifscCode=" + ifscCode + ", bankName=" + bankName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", address=" + address + "]";
	}

	public Bank(Long id, String accountHolder, String accountNumber, BigDecimal balance, String ifscCode, String bankName,
			String email, String mobileNumber, String address) {
		super();
		this.id = id;
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	
	
}
