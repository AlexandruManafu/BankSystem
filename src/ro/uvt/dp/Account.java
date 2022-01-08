package ro.uvt.dp;

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

	@Override
	public void depose(double amount) {

		this.amount += amount;
	}

	@Override
	public void retrieve(double amount) {

		this.amount -= amount;
	}

	public String toString() {
		return "Account: IBAN=" + iban + ", amount=" + amount;
	}

	public String getAccountNumber() {
		return iban;
	}

}
