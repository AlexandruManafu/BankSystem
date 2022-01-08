package ro.uvt.dp;

public interface Operations {
	double getTotalAmount();

	double getInterest();

	void depose(double amount);

	void retrieve(double amount);
}
