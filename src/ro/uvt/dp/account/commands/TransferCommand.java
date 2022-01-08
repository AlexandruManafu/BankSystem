package ro.uvt.dp.account.commands;

import ro.uvt.dp.account.*;
import ro.uvt.dp.exceptions.InvalidTransferAmount;

public class TransferCommand implements AccountCommand{
	private Account source;
	private Account destination;
	private double amount;
	
	public TransferCommand(Account destination, Account source,double amount)
	{
		this.source = source;
		this.destination = destination;
		this.amount = amount;		
	}

	@Override
	public void execute() throws InvalidTransferAmount {
		destination.transfer(source, amount);
	}
	
	@Override
	public void undo() throws InvalidTransferAmount {
		source.transfer(destination, amount);
	}

}
