package ro.uvt.dp.client;

import ro.uvt.*;
import ro.uvt.dp.exceptions.*;
import java.util.Arrays;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.AccountEUR;
import ro.uvt.dp.account.AccountFactory;
import ro.uvt.dp.account.AccountRON;
import ro.uvt.dp.account.Account.TYPE;

import java.util.ArrayList;

public class Client {
	public static final int MAX_ACCOUNTS_NUMBER = 5;

	private String name;
	private String address;
	private ArrayList<Account> accounts;

	public Client(ClientBuilder builder) 
			throws DuplicateIBAN, ClientsLimitExceeded, InvalidTransferAmount {
		this.name = builder.name;
		this.address = builder.address;
		
		accounts = new ArrayList<Account>();
		
		// If the builder has the account fields set then create one
		if(builder.IBAN.length() > 0 && builder.type != null)
		{
			Account newAccount = AccountFactory.createAccount(builder.type, builder.IBAN, builder.sum);
			addAccount(newAccount);
		}

	}
	/**
	 * Check if the account with the IBAN exists
	 * @param iban - String
	 * @return true if an account with the IBAN is found, false otherwise
	 */
	public Boolean ibanExists(String iban)
	{
		for (int i = 0; i < accounts.size(); i++) {
			String accountNr = accounts.get(i).getAccountNumber();
			if (accountNr.equals(iban)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Add the account to the accounts list if there is no account with numarCont
	 * @param tip - Possible values: Account.TYPE.EUR, Account.TYPE.RON
	 * @param numarCont - String
	 * @param suma - Initial amount of money
	 */
	public void addAccount(Account account) throws DuplicateIBAN, ClientsLimitExceeded  {
			
		if(this.ibanExists(account.getAccountNumber()))
		{
			throw new DuplicateIBAN("For the add operation, An account with the same IBAN exists.");
		}
		
		if(accounts.size()<MAX_ACCOUNTS_NUMBER)
			accounts.add(account);
		else
			throw new ClientsLimitExceeded(
					"Client addAccount method over the limit of MAX_ACCOUNTS_NUMBER");
	}
	

	/**
	 * Get the account if it is found
	 * @param accountCode - String used to find the target account
	 * @return account, or null if no account with accountCode is found 
	 */
	public Account getAccount(String accountCode) throws AccountNotFound{
		for (int i = 0; i < accounts.size(); i++) {
			String accountNr = accounts.get(i).getAccountNumber();
			if (accountNr.equals(accountCode)) {
				return accounts.get(i);
			}
		}
		throw new AccountNotFound("No account found for the get operation");
	}
	
	public int getNumberAccounts()
	{
		return accounts.size();
	}
	
	/**
	 * Delete the account with matching accountCode
	 * @param accountCode - String
	 */
	public void deleteAccount(String accountCode) throws AccountNotFound
	{
		boolean foundAccount = false;
		for (int i = 0; i < accounts.size(); i++) {
			String accountNr = accounts.get(i).getAccountNumber();
			if (accountNr.equals(accountCode)) {
				accounts.remove(i);
				foundAccount = true;
			}
		}
		if(!foundAccount)
			throw new AccountNotFound("No account found for the delete operation");
	}

	@Override
	public String toString() {
		return "\n\tClient [name=" + name + ", address=" + address +
				", acounts=" + accounts.toString() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String nume) {
		this.name = nume;
	}
}
