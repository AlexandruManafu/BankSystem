package ro.uvt.dp.account;

import ro.uvt.dp.exceptions.InvalidTransferAmount;

//Implement Command for ATM, transfer between 2 accounts with an undo option

public abstract class Account implements Operations {

	protected String iban = null;
	protected double amount = 0;

	public static enum TYPE {
		EUR, RON
	};
	
	protected Account()
	{
		
	}

	protected Account(String iban, double suma) throws InvalidTransferAmount {
		this.iban = iban;
		depose(suma);
	
	}

	@Override
	public double getTotalAmount() {

		return amount + amount * getInterest();
	}
	
	public double getAmount() {
		return amount;
	}

	
	@Override
	public void depose(double amount) throws InvalidTransferAmount {

		if(amount>=0)
			this.amount += amount;
		else
			throw new InvalidTransferAmount("Account depose negative or 0 value");
	}

	@Override
	public void retrieve(double amount) throws InvalidTransferAmount {
		if(amount>0 && amount<= this.amount)
			this.amount -= amount;
		else
			throw new InvalidTransferAmount("Account retrieve negative or 0 value");
	}

	public String toString() {
		return "Account: IBAN=" + iban + ", amount=" + amount;
	}
	
	public void transfer(Account c, double s) throws InvalidTransferAmount
	{
		throw new InvalidTransferAmount("Cannot call transfer from an abstract class");
	}

	public String getAccountNumber() {
		return iban;
	}

}
