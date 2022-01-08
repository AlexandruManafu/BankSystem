package ro.uvt.dp.account;


import ro.uvt.dp.account.Account.TYPE;
import ro.uvt.dp.exceptions.InvalidTransferAmount;
// Factory pattern for account that will either make an EUR or RON account
public abstract class AccountFactory{

	public static Account createAccount(TYPE type, String IBAN, double amount) throws InvalidTransferAmount
	{
		if(type.equals(TYPE.EUR))
			return new AccountEUR(IBAN,amount);
		else if(type.equals(TYPE.RON))
			return new AccountRON(IBAN,amount);
		else
			 throw new IllegalArgumentException("Type must be RON or EUR");
	}

}
