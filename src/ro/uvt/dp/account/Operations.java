package ro.uvt.dp.account;

import ro.uvt.dp.exceptions.InvalidTransferAmount;

public interface Operations {
	
	double getRatioToEur();
	
	double getTotalAmount();

	double getInterest();

	void depose(double amount) throws InvalidTransferAmount;

	void retrieve(double amount) throws InvalidTransferAmount;
}
