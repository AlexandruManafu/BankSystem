package ro.uvt.dp.gui.model;

import ro.uvt.dp.bank.*;
import ro.uvt.dp.account.*;
import ro.uvt.dp.client.*;
import ro.uvt.dp.exceptions.*;

public class NewClientModel {
	
	private Bank bank;
	private int ronId=0;
	private int eurId=0;
	
	public NewClientModel(Bank bank)
	{
		this.bank = bank;
	}
	
	public void performAction(String action, String name, String type) throws ClientsLimitExceeded, ClientNotFound
	{
		if(action.equals("New Client"))
		{
			try {
				
				ClientBuilder builder = new ClientBuilder();
				builder = builder.name(name);
				Client c = new Client(builder);
				bank.addClient(c);
				
			}catch( DuplicateIBAN | InvalidTransferAmount e)
			{
				e.printStackTrace();
			}
		}
		else if(action.equals("New Account"))
		{
			try {		
				Client target = bank.getClient(name);
				String id = "";
				if(type.equals("RON"))
				{
					id +="RON"+ronId;
					ronId = ronId + 1;
					Account account = new AccountRON(id,0);
					target.addAccount(account);
				}
				else if(type.equals("EUR"))
				{
					id +="EUR"+eurId;
					eurId = eurId + 1;
					Account account = new AccountEUR(id,0);
					target.addAccount(account);
				}
				
			} catch (InvalidTransferAmount | DuplicateIBAN e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Bank getBank()
	{
		return this.bank;
	}
	

}
