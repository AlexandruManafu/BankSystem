package ro.uvt.dp.gui.model;

import java.util.ArrayList;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.client.Client;
import ro.uvt.dp.exceptions.AccountNotEmpty;
import ro.uvt.dp.exceptions.AccountNotFound;
import ro.uvt.dp.exceptions.ClientNotFound;

public class SelectAccountModel {
	
	private Bank bank;
	private String currentClient = "";
	private String currentAccount = "";
	
	public SelectAccountModel(Bank bank)
	{
		this.bank = bank;
	}
	
	public boolean login(String clientName)
	{
		if(bank.clientExists(clientName))
			this.currentClient = clientName;
		return bank.clientExists(clientName);
	}
	
	private ArrayList<Account> getAccounts()
	{
		try {
			Client client = bank.getClient(currentClient);
			return client.getAccounts();
			
		} catch (ClientNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getAccountsNames()
	{
		ArrayList<Account> accounts = getAccounts();
		int length = accounts.size();
		String[] result = new String[length];
		
		for(int i = 0;i<length;i++)
		{
			result[i] = accounts.get(i).getAccountNumber();
		}
		
		return result;
	}
	
	public double getBalance() throws AccountNotFound, ClientNotFound
	{
		return bank.getClient(currentClient).getAccount(currentAccount).getAmount();
	}
	
	public boolean hasMoney() throws AccountNotFound, ClientNotFound
	{
		return getBalance() > 0;
	}
	
	public void deleteAccount(String accountName) throws AccountNotFound, ClientNotFound, AccountNotEmpty
	{
		bank.getClient(currentClient).deleteAccount(accountName);
	}
	
	public Bank getBank()
	{
		return bank;
	}
	
	public Account getAccountAsObject() throws AccountNotFound, ClientNotFound
	{
		return bank.getClient(currentClient).getAccount(currentAccount);
	}
	
	public void printBank()
	{
		System.out.println(bank);
	}
	
	public String getCurrentClient()
	{
		return currentClient;
	}
	
	public void setCurrentAccount(String value)
	{
		this.currentAccount = value;
	}
	
	public String getCurrentAccount()
	{
		return currentAccount;
	}

}
