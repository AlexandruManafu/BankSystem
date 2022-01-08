package ro.uvt.dp.account;

import ro.uvt.dp.exceptions.InvalidTransferAmount;

public interface Transfer {
	 void transfer(Account c, double s) throws InvalidTransferAmount;
}
