package ro.uvt.dp.account.commands;

import ro.uvt.dp.exceptions.InvalidTransferAmount;

public interface AccountCommand {
	
	public void execute() throws InvalidTransferAmount;
	public void undo() throws InvalidTransferAmount;

}
