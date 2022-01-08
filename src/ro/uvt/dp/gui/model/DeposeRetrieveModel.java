package ro.uvt.dp.gui.model;

import java.util.ArrayList;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.client.Client;
import ro.uvt.dp.exceptions.AccountNotFound;
import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.exceptions.InvalidTransferAmount;

public class DeposeRetrieveModel {
	
	private Bank bank;
	
	private SelectAccountModel dependency;
	
	public DeposeRetrieveModel(SelectAccountModel dependency)
	{
		this.dependency = dependency;
		this.bank = dependency.getBank();
		
	}
	
	public void printBank()
	{
		dependency.printBank();
	}
	
	public boolean accountSelected()
	{
		return !dependency.getCurrentAccount().isEmpty();
	}
	
	public void doAction(String action, double amount) throws InvalidTransferAmount
	{
		String clientName = dependency.getCurrentClient();
		String accountName = dependency.getCurrentAccount();
		try {
			
			if(action.equals("Depose"))
				bank.getClient(clientName).getAccount(accountName).depose(amount);
			else if(action.equals("Retrieve"))
				bank.getClient(clientName).getAccount(accountName).retrieve(amount);
			
		} catch (AccountNotFound | ClientNotFound e) {
			e.printStackTrace();
		}
	}

}
