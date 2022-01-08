package ro.uvt.dp.account;

public class EconomyAccount extends AccountDecorator {

	public EconomyAccount(Account account) {
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
		// TODO Auto-generated method stub
		return 1.1 * super.account.getInterest();
	}

}
