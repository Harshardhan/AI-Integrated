package dailypractice;

import java.math.BigDecimal;

public class Order {

	private Long id;
	private String customerName;
	private String orderId;
	private String mobileNumber;
	private BigDecimal amount;
	private PaymentMethod paymentMethod;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Order() {
		
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Order(Long id, String customerName, String orderId, String mobileNumber, BigDecimal amount,
			PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.orderId = orderId;
		this.mobileNumber = mobileNumber;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customerName=" + customerName + ", orderId=" + orderId + ", mobileNumber="
				+ mobileNumber + ", amount=" + amount + ", paymentMethod=" + paymentMethod + "]";
	}
	
	
}
