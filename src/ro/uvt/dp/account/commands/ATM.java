package ro.uvt.dp.account.commands;

import ro.uvt.dp.exceptions.InvalidTransferAmount;

public class ATM {
	private AccountCommand command;
	private int operationsCount = 0;
	
	public ATM(AccountCommand command)
	{
		this.command = command;
	}
	
	public void execute() throws InvalidTransferAmount
	{
		command.execute();
		operationsCount++;
	}
	
	public void undo() throws InvalidTransferAmount
	{
		if(operationsCount>0)
		{
			command.undo();
			operationsCount--;
		}else
		{
			InvalidTransferAmount e =
					new InvalidTransferAmount("Can't undo without previous opeations");
			throw(e);
		}
	}
	
}
