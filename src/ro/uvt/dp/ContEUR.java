package ro.uvt.dp;

public class ContEUR extends Account {

	public ContEUR(String iban, double amount) {
		super(iban, amount);
	}

	public double getInterest() {
		return 0.01;

	}

	@Override
	public String toString() {
		return "Account EUR [" + super.toString() + "]";
	}
}
