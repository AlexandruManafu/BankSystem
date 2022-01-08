package ro.uvt.dp.client;


import ro.uvt.dp.exceptions.*;
import java.util.Arrays;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.AccountEUR;
import ro.uvt.dp.account.AccountRON;
import ro.uvt.dp.account.Account.TYPE;

import java.util.ArrayList;

//Builder design pattern for Client
public class ClientBuilder {

	String name;
	String address;
	ArrayList<Account> accounts;
	TYPE type = null;
	String IBAN = "";
	double sum = 0;

	public ClientBuilder name(String name) {
		this.name = name;
		
		return this;
	}
	
	public ClientBuilder address(String address) {
		this.address = address;
		
		return this;
	}
	
	public ClientBuilder account(TYPE type, String IBAN, double sum) {
		this.type = type;
		this.IBAN = IBAN;
		this.sum = sum;
		
		return this;
	}
	
	public Client build() throws DuplicateIBAN, ClientsLimitExceeded, InvalidTransferAmount
	{
		return new Client(this);
	}
	
	
}
