package ro.uvt.dp.account;

public class PrivateInsuranceAccount extends AccountDecorator {

	protected Account account;

	public PrivateInsuranceAccount(Account account) {
		super(account);
	
	}
	
	@Override
	public double getTotalAmount() {

		return amount + amount * getInterest();
	}

	@Override
	public double getRatioToEur() {
		return super.account.getRatioToEur();
	}

	@Override
	public double getInterest() {
		return 0.8 * super.account.getInterest();
	}
	

}
