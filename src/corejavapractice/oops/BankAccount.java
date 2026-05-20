package corejavapractice.oops;

import java.math.BigDecimal;

public class BankAccount {

	private String bankAccountNumber;
	
	private BigDecimal balance;
	
	private String accountHolder;

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountHolder
	 */
	public String getAccountHolder() {
		return accountHolder;
	}

	/**
	 * @param accountHolder the accountHolder to set
	 */
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	/**
	 * @param bankAccountNumber
	 * @param accountHolder
	 * @param balance
	 */
	public BankAccount(String bankAccountNumber, String accountHolder, BigDecimal balance) {
		super();
		this.bankAccountNumber = bankAccountNumber;
		this.balance = balance;
		this.accountHolder = accountHolder;
	}
	
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
            System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
            System.out.println("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
    // Main method for testing the BankAccount class
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("123456789", "John Doe", new BigDecimal("500.00"));

        // Display initial account details
        System.out.println("Account Holder: " + myAccount.getAccountHolder());
        System.out.println("Account Number: " + myAccount.getBankAccountNumber());
        System.out.println("Initial Balance: " + myAccount.getBalance());

        // Test deposit and withdrawal
        myAccount.deposit(new BigDecimal("200.00")); // Deposit valid amount
        myAccount.withdraw(new BigDecimal("100.00")); // Withdraw valid amount
        myAccount.withdraw(new BigDecimal("700.00")); // Withdraw invalid amount
        myAccount.deposit(new BigDecimal("-50.00")); // Deposit invalid amount
    }

	
	
	
	
}
