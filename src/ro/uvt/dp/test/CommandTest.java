package ro.uvt.dp.test;

import ro.uvt.dp.account.commands.*;
import ro.uvt.dp.account.*;
import static org.junit.Assert.*;

import org.junit.Test;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.account.Account.TYPE;
import ro.uvt.dp.account.AccountEUR;
import ro.uvt.dp.account.AccountFactory;
import ro.uvt.dp.account.AccountRON;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.client.*;

import ro.uvt.dp.exceptions.*;
public class CommandTest {
	
	Bank bcr = AccountTest.createBCRBank();
	
	@Test
	public void validTransferCommand()
	{
		try {
		AccountRON c1 = (AccountRON) this.bcr.getClient("Marinescu Marin").getAccount("RON126");
		AccountRON c2 = (AccountRON) this.bcr.getClient("Ionescu Ion").getAccount("RON1234");
		
		double initial1 = c1.getAmount();
		double initial2 = c2.getAmount();
		
		TransferCommand t = new TransferCommand(c1,c2,10);
		
		ATM a = new ATM(t);
		a.execute();
		a.execute();
		
		assertEquals(c1.getAmount(), initial1 + 20, 0);
		assertEquals(c2.getAmount(), initial2 - 20, 0);
		}catch(Exception e)
		{}
	}
	
	@Test
	public void invalidUndo()
	{
		try {
		AccountRON c1 = (AccountRON) this.bcr.getClient("Marinescu Marin").getAccount("RON126");
		AccountRON c2 = (AccountRON) this.bcr.getClient("Ionescu Ion").getAccount("RON1234");
		
		double initial1 = c1.getAmount();
		double initial2 = c2.getAmount();
		
		TransferCommand t = new TransferCommand(c1,c2,10);
		
		ATM a = new ATM(t);
		a.execute();
		a.undo();
		a.undo();
		
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
	
	

	
	
    
	
}