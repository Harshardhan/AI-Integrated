package revision;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Bank myAccount = new Bank(
                1L,
                "Harshavardhan",
                "32456236",
                new BigDecimal("500.00"),
                "SIBN000123",
                "StateBank",
                "harsha@example.com",
                "9160819142",
                "Tirupati"
        );

        // 🔥 Initialize service with the account
        BankAccountService account = new BankAccountServiceImpl(myAccount);

        System.out.println("Account Holder: " + myAccount.getAccountHolder());
        System.out.println("Account Number: " + myAccount.getAccountNumber());
        System.out.println("Initial Balance: " + myAccount.getBalance());

        try {
            account.deposit(new BigDecimal("200.00"));
            account.withdraw(new BigDecimal("100.00"));
            account.withdraw(new BigDecimal("700.00")); // should throw exception
            account.deposit(new BigDecimal("-50.00"));
        } catch (InSufficientBalanceException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("Final Balance: " + account.checkBalance());
    }
}
