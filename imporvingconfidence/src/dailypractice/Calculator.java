package dailypractice;

import java.math.BigDecimal;

public class Calculator {

    private final BigDecimal amount;
    private final String currency;

    public Calculator(BigDecimal amount, String currency) {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Amount and currency must not be null");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

	@Override
	public String toString() {
		return "Calculator [amount=" + amount + ", currency=" + currency + "]";
	}
    
    
}
