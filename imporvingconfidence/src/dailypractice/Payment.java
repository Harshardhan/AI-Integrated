package dailypractice;

import java.math.BigDecimal;

public class Payment {

	private Long id;
	private String orderId;
	private String customerName;
	private BigDecimal amount;
	private String mobileNumber;
	private PaymentMethod paymentMethod;
	public Payment(Long id, String orderId, String customerName, BigDecimal amount, String mobileNumber,
			PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.customerName = customerName;
		this.amount = amount;
		this.mobileNumber = mobileNumber;
		this.paymentMethod = paymentMethod;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	
	public Payment() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", orderId=" + orderId + ", customerName=" + customerName + ", amount=" + amount
				+ ", mobileNumber=" + mobileNumber + ", paymentMethod=" + paymentMethod + "]";
	}

	
	
	
}
