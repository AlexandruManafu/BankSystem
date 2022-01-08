package ro.uvt.dp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.AccountEUR;
import ro.uvt.dp.account.AccountFactory;
import ro.uvt.dp.account.AccountRON;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.client.*;

import ro.uvt.dp.exceptions.*;
public class AccountTest {
	
	Bank bcr = createBCRBank();
	//Bank cec = createCECBank();
	
	
	
	@Test
	public void testValidRetrieveDepose()
	{
		// depose in account RON126 of client Marinescu
		try {
		Client cl = this.bcr.getClient("Marinescu Marin");
		Account target = cl.getAccount("RON126");
		double initialMoney = target.getAmount();
		
		
		// depose in account RON126 of client Marinescu
		target.depose(400);
		double afterDepose = target.getAmount();
		
		assertEquals(initialMoney + 400, afterDepose, 0  );

		// retrieve from account RON126 of Marinescu client
		target.retrieve(67);
		double afterRetrieve = target.getAmount();
		
	
		assertEquals(afterDepose-67, afterRetrieve,0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInvalidDepose() throws InvalidTransferAmount
	{
		// depose in account RON126 of client Marinescu
		Account target = null;
		try {
		Client cl = this.bcr.getClient("Marinescu Marin");
		target = cl.getAccount("RON126");
		// depose in account RON126 of client Marinescu
		target.depose(-1);
		}catch(Exception e)
		{
			e.printStackTrace();
			if(e instanceof InvalidTransferAmount)
			{
				ClientTest.passTest();
			}
			else
				fail();
		}
		
	}
	
	@Test
	public void testInvalidRetrieve() throws InvalidTransferAmount
	{
		// depose in account RON126 of client Marinescu
		Account target = null;
		try {
		Client cl = this.bcr.getClient("Marinescu Marin");
		target = cl.getAccount("RON126");
		// depose in account RON126 of client Marinescu
		target.retrieve(-1);
		}catch(InvalidTransferAmount | ClientNotFound | AccountNotFound e)
		{
			e.printStackTrace();
			if(e instanceof InvalidTransferAmount)
			{
				ClientTest.passTest();
			}
			else
				fail();
		}
		
	}
	
	@Test
	public void testValidTransfer()
	{
		try {
		// tranfer between accounts RON126 and RON1234
		AccountRON c1 = (AccountRON) this.bcr.getClient("Marinescu Marin").getAccount("RON126");
		AccountRON c2 = (AccountRON) this.bcr.getClient("Ionescu Ion").getAccount("RON1234");
		double c1InitialAmount = c1.getAmount();
		double c2InitialAmount = c2.getAmount();
		
		c1.transfer(c2, 40);
		assertEquals(c1.getAmount(), c1InitialAmount + 40,0);
		assertEquals(c2.getAmount(), c2InitialAmount - 40,0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}
	
	@Test
	public void testValidTransferRonEur()
	{
		try {
		AccountRON c1 = (AccountRON) this.bcr.getClient("Marinescu Marin").getAccount("RON126");
		AccountEUR c2 = (AccountEUR) this.bcr.getClient("Ionescu Ion").getAccount("EUR124");
		double c1InitialAmount = c1.getAmount();
		double c2InitialAmount = c2.getAmount();
		
		//Transfer 10 eur to a ron account
		c1.transfer(c2, 10);
		assertEquals(c1.getAmount(), c1InitialAmount + 10*c1.getRatioToEur(), 0);
		assertEquals(c2.getAmount(), c2InitialAmount - 10, 0);
		
		c1InitialAmount = c1.getAmount();
		c2InitialAmount = c2.getAmount();
		
		//Transfer 5 ron to an eur account
		c2.transfer(c1, 5);
		assertEquals(c2.getAmount(), c2InitialAmount + 5/c1.getRatioToEur(), 0);
		assertEquals(c1.getAmount(), c1InitialAmount - 5, 0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
    public Bank createBCRBank()
	{
    	Bank bcr = null;
    	try {
		//BCR bank has 2 clients 
		bcr = Bank.getBank("Banca BCR");
		
		// create Ionescu client with builder pattern including a default account
		ClientBuilder builder = new ClientBuilder();
		builder = builder.name("Ionescu Ion");
		builder = builder.address("Timisoara");
		builder = builder.account(Account.TYPE.EUR, "EUR124", 200.9);
		
		Client cl1 = new Client(builder);
		bcr.addClient(cl1);
		
		Account a1 = AccountFactory.createAccount(Account.TYPE.RON, "RON1234", 400);
		
		cl1.addAccount(a1);

		// create Marinecu client with builder pattern add account after
		builder = new ClientBuilder();
		builder = builder.name("Marinescu Marin");
		builder = builder.address("Timisoara");
		Client cl2 = new Client(builder);
		
		Account a2 = AccountFactory.createAccount(Account.TYPE.RON, "RON126", 100);
		
		cl2.addAccount(a2);
		
		bcr.addClient(cl2);
		


    	}catch(Exception e)
    	{
    		
    	}
    	
    	return bcr;
		
	}
	
	public Bank createCECBank()
	{
		Bank cec = null;
		try {
		//CEC bank has 1 client
		cec = Bank.getBank("Banca CEC");
		ClientBuilder builder = new ClientBuilder();
		builder = builder.name("Vasilescu Vasile");
		builder = builder.address("Brasov");
		builder = builder.account(Account.TYPE.EUR, "EUR128", 700);
		Client clientCEC = new Client(builder);
			cec.addClient(clientCEC);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(cec);
		
		return cec;
	
	}
	
}