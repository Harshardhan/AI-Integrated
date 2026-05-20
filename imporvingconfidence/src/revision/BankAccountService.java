package revision;

import java.math.BigDecimal;

public interface BankAccountService {

	 Bank deposit(BigDecimal amt);
	
	 Bank withdraw(BigDecimal amt)throws InSufficientBalanceException;
	
	 BigDecimal checkBalance() ;
}
