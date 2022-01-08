package ro.uvt.dp.account;

public abstract class AccountDecorator extends Account {

	protected Account account;

	public AccountDecorator(Account account) {
	this.account = account;
	
	}

}
