package ro.uvt.dp.account;

public class AccountEUR extends Account implements Transfer {
	

	public AccountEUR(String iban, double amount) {
		super(iban, amount);
	}

	public double getInterest() {
		return 0.01;

	}
	
	@Override
	public double getRatioToEur()
	{
		return 1;
	}
	
	@Override
	/**
	 * Transfer money from the target to the current account
	 * @param target - Account
	 * @param amount - Double
	 * Amount is converted to EUR
	 */
	public void transfer(Account target, double amount)
	{
		target.retrieve(amount);
		Double ratio = target.getRatioToEur();
		depose(amount / ratio);

	}

	@Override
	public String toString() {
		return "Account EUR [" + super.toString() + "]";
	}
}
