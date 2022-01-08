package ro.uvt.dp.account;

public class AccountRON extends Account implements Transfer {
	

	public AccountRON(String iban, double amount) {
		super(iban, amount);
	}

	public double getInterest() {
		if (amount < 500)
			return 0.03;
		else
			return 0.08;

	}
	
	@Override
	public double getRatioToEur()
	{
		return 5;
	}

	@Override
	public String toString() {
		return "Account RON [" + super.toString() + "]";
	}

	@Override
	/**
	 * Transfer money from the target to the current account
	 * @param target - Account
	 * @param s - Double
	 * Amount is converted to RON from EUR if the target is an EUR account
	 */
	public void transfer(Account target, double s) {
		target.retrieve(s);
		Double amount = s;
		if(target instanceof AccountEUR)
		{
			amount = this.getRatioToEur() * amount;
		}
		depose(amount);
	}
}