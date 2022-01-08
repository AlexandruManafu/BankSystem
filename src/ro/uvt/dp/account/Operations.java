package ro.uvt.dp.account;

public interface Operations {
	
	double getRatioToEur();
	
	double getTotalAmount();

	double getInterest();

	void depose(double amount);

	void retrieve(double amount);
}
