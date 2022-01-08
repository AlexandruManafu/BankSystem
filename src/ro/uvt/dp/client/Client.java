package ro.uvt.dp.client;

import java.util.Arrays;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.AccountEUR;
import ro.uvt.dp.account.AccountRON;
import ro.uvt.dp.account.Account.TYPE;

import java.util.ArrayList;

public class Client {
	public static final int MAX_ACCOUNTS_NUMBER = 5;

	private String name;
	private String address;
	private ArrayList<Account> accounts;

	public Client(String nume, String adresa, TYPE tip, String numarCont, double suma) {
		this.name = nume;
		this.address = adresa;
		accounts = new ArrayList<Account>();
		addAccount(tip, numarCont, suma);
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
	public void addAccount(TYPE tip, String numarCont, double suma) {
		Account c = null;
		if (tip == Account.TYPE.EUR)
			c = new AccountEUR(numarCont, suma);
		else if (tip == Account.TYPE.RON)
			c = new AccountRON(numarCont, suma);
		
		if(this.ibanExists(numarCont))
		{
			System.out.println("Warning, Client addAccount IBAN already exists");
			return;
		}
		
		if(accounts.size()<MAX_ACCOUNTS_NUMBER)
			accounts.add(c);
		else
			System.out.println("Error, "
					+ "Client addAccount method over the limit of MAX_ACCOUNTS_NUMBER");
	}

	/**
	 * Get the account if it is found
	 * @param accountCode - String used to find the target account
	 * @return account, or null if no account with accountCode is found 
	 */
	public Account getAccount(String accountCode) {
		for (int i = 0; i < accounts.size(); i++) {
			String accountNr = accounts.get(i).getAccountNumber();
			if (accountNr.equals(accountCode)) {
				return accounts.get(i);
			}
		}
		return null;
	}
	
	public int getNumberAccounts()
	{
		return accounts.size();
	}
	
	/**
	 * Delete the account with matching accountCode
	 * @param accountCode - String
	 */
	public void deleteAccount(String accountCode)
	{
		for (int i = 0; i < accounts.size(); i++) {
			String accountNr = accounts.get(i).getAccountNumber();
			if (accountNr.equals(accountCode)) {
				accounts.remove(i);
			}
		}
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
