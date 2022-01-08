package ro.uvt.dp.test;
import ro.uvt.dp.*;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.client.Client;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientTest {

	@Test
	public void createDeleteAccount() {
		Client c = new Client("Alex","000",Account.TYPE.RON,"0",0);
		c.addAccount(Account.TYPE.RON, "RON1234", 400);
		c.addAccount(Account.TYPE.RON, "RON1234", 400);
		//Note that one account is created when a client is created
		//Duplicated codes not allowed
		
		assertEquals(c.getNumberAccounts(),2,0);
		
		c.deleteAccount("RON1234");
		assertEquals(c.getNumberAccounts(),1,0);
		
	}
	
};
