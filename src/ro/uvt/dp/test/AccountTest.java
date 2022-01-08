package ro.uvt.dp.test;

import static org.junit.Assert.*;
import org.junit.Test;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.AccountEUR;
import ro.uvt.dp.account.AccountRON;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.client.Client;

public class AccountTest {
	
	Bank bcr = createBCRBank();
	Bank cec = createCECBank();
	
	@Test
	public void testRetrieveDepose()
	{
		// depose in account RON126 of client Marinescu
		Client cl = this.bcr.getClient("Marinescu Marin");
		Account target = cl.getAccount("RON126");
		double initialMoney = target.getAmount();
		
		
		// depose in account RON126 of client Marinescu
		target.depose(400);
		target.depose(-1000);
		double afterDepose = target.getAmount();
		
		//System.out.println("depose " + initialMoney + " " + afterDepose);
		assertEquals(initialMoney + 400, afterDepose, 0  );

		// retrieve from account RON126 of Marinescu client
		target.retrieve(67);
		target.retrieve(-9999);
		double afterRetrieve = target.getAmount();
		
		//System.out.println("retrieve "+ afterDepose + " " + afterRetrieve);
	
		assertEquals(afterDepose-67, afterRetrieve,0);
		
	}
	
	@Test
	public void testTransfer()
	{
		// tranfer between accounts RON126 and RON1234
		AccountRON c1 = (AccountRON) this.bcr.getClient("Marinescu Marin").getAccount("RON126");
		AccountRON c2 = (AccountRON) this.bcr.getClient("Ionescu Ion").getAccount("RON1234");
		double c1InitialAmount = c1.getAmount();
		double c2InitialAmount = c2.getAmount();
		
		c1.transfer(c2, 40);
		assertEquals(c1.getAmount(), c1InitialAmount + 40,0);
		assertEquals(c2.getAmount(), c2InitialAmount - 40,0);
		
	}
	
	@Test
	public void testTransferRonEur()
	{
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
	}
	
	
    public Bank createBCRBank()
	{
		
		//BCR bank has 2 clients 
		Bank bcr = new Bank("Banca BCR");
		// create Ionescu client 
		Client cl1 = new Client("Ionescu Ion", "Timisoara", Account.TYPE.EUR, "EUR124", 200.9);
		bcr.addClient(cl1);
		cl1.addAccount(Account.TYPE.RON, "RON1234", 400);
		
		// create Marinecu client
		Client cl2 = new Client("Marinescu Marin", "Timisoara", Account.TYPE.RON, "RON126", 100);
		bcr.addClient(cl2);
		//System.out.println(bcr);
		
		return bcr;
	}
	
	public Bank createCECBank()
	{
		//CEC bank has 1 client
		Bank cec = new Bank("Banca CEC");
		Client clientCEC = new Client("Vasilescu Vasile", "Brasov", Account.TYPE.EUR, "EUR128", 700);
		cec.addClient(clientCEC);
		//System.out.println(cec);
		
		return cec;
	}
	
}