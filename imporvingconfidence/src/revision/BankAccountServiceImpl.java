package revision;

import java.math.BigDecimal;

public class BankAccountServiceImpl implements BankAccountService {

	private Bank bank;

	public BankAccountServiceImpl(Bank bank) {
		super();
		this.bank = bank;
	}

	@Override
	public Bank deposit(BigDecimal amt) {
		if (amt.compareTo(BigDecimal.ZERO) > 0) {
			System.out.println("Deposit amount must be positive.");
			return bank;
		}
		BigDecimal newBalance = bank.getBalance().add(amt);
		bank.setBalance(newBalance);
		System.out.println("Deposited: " + amt + ", New Balance: " + newBalance);
		return bank;
	}

	@Override
	public Bank withdraw(BigDecimal amt) throws InSufficientBalanceException {
		
		if(amt.compareTo(amt) <= 0) {
			System.out.println("withdrawal amount must be positive.");
			return bank;
		}
		if(bank.getBalance().compareTo(amt) < 0) {
			throw new InSufficientBalanceException("Insufficient balance...");
		}
		
		BigDecimal updatedBalance = bank.getBalance().subtract(amt);
		bank.setBalance(updatedBalance);
	    System.out.println("Withdrew: " + amt + ", New Balance: " + updatedBalance);

		return bank;
	}
		

	@Override
	public BigDecimal checkBalance() {
				
		return bank.getBalance();
	}

}
