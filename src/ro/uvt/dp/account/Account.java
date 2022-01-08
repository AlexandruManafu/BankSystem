package ro.uvt.dp.account;

public abstract class Account implements Operations {

	protected String iban = null;
	protected double amount = 0;

	public static enum TYPE {
		EUR, RON
	};

	protected Account(String iban, double suma) {
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
	public void depose(double amount) {

		if(amount>0)
			this.amount += amount;
		else
			System.out.println("Warning, Account depose negative or 0 value");
	}

	@Override
	public void retrieve(double amount) {
		//first throw exception
		if(amount>0)
			this.amount -= amount;
		else
			System.out.println("Warning, Account retrieve negative or 0 value");
	}

	public String toString() {
		return "Account: IBAN=" + iban + ", amount=" + amount;
	}

	public String getAccountNumber() {
		return iban;
	}

}
