package ro.uvt.dp.test;
import ro.uvt.dp.*;


import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.AccountFactory;
import ro.uvt.dp.client.Client;
import ro.uvt.dp.client.ClientBuilder;
import ro.uvt.dp.exceptions.AccountNotFound;
import ro.uvt.dp.exceptions.ClientsLimitExceeded;
import ro.uvt.dp.exceptions.DuplicateIBAN;
import ro.uvt.dp.exceptions.InvalidTransferAmount;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientTest {
	
	public static void passTest()
	{
		assertEquals(1,1,0);
	}

	@Test
	public void createDeleteAccount() {
		try {
		ClientBuilder builder = new ClientBuilder();
		builder = builder.name("Ionescu Ion");
		builder = builder.address("Timisoara");
		
		Account a1 = AccountFactory.createAccount(Account.TYPE.RON, "RON126", 100);
		Account a2 = AccountFactory.createAccount(Account.TYPE.RON, "RON1234", 400);
		
		Client c = new Client(builder);
		
		c.addAccount(a1);
		c.addAccount(a2);
		//Note that one account is created when a client is created
		//Duplicated codes not allowed
				
		c.deleteAccount("RON1234");
		
		//Check for successful deletion
		assertEquals(c.getNumberAccounts(),1,0);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void addInvalidAccount() {
		
		try {
		ClientBuilder builder = new ClientBuilder();
		builder = builder.name("Ionescu Ion");
		builder = builder.address("Timisoara");
		Client c = new Client(builder);
		
		Account a2 = AccountFactory.createAccount(Account.TYPE.RON, "RON1234", 400);
		
		c.addAccount(a2);
		c.addAccount(a2);
		
		if(c.getNumberAccounts()==2)
			fail("");
		
		}catch(ClientsLimitExceeded | DuplicateIBAN | InvalidTransferAmount e)
		{
			e.printStackTrace();
			
			if(e instanceof DuplicateIBAN )
			{
				passTest();
			}
			else
				fail("");
		}

	}
	
	@Test
	public void addAccountsOverLimit(){
		try {
			
		ClientBuilder builder = new ClientBuilder();
		builder = builder.name("Ionescu Ion");
		builder = builder.address("Timisoara");
		//builder.build
		Client c = new Client(builder);
		
		String iban = "RON1234";
		for(int i = 0; i<7;i++)
		{
			iban += "1";
			Account a1 = AccountFactory.createAccount(Account.TYPE.RON, iban , 400);
			c.addAccount(a1);	
		}
		
		if(c.getNumberAccounts()==6)
			fail("");
		
		}catch(ClientsLimitExceeded | DuplicateIBAN | InvalidTransferAmount e)
		{
			e.printStackTrace();
			if(e instanceof ClientsLimitExceeded )
			{
				passTest();
			}
			else
				fail("");
		}
	}
	
	
};
