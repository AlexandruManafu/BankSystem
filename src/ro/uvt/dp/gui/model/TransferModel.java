package ro.uvt.dp.gui.model;

import java.util.ArrayList;

import ro.uvt.dp.account.Account;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.client.Client;
import ro.uvt.dp.exceptions.AccountNotFound;
import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.exceptions.InvalidTransferAmount;

public class TransferModel {
	
	private Bank bank;
	
	private SelectAccountModel dependency;
	
	public TransferModel(SelectAccountModel dependency)
	{
		this.dependency = dependency;
		this.bank = dependency.getBank();
		
	}
	
	public double getBalance() throws AccountNotFound, ClientNotFound
	{
		return dependency.getBalance();
	}
	
	public void printBank()
	{
		dependency.printBank();
	}
	
	public void transfer(String target, double amount) throws AccountNotFound, ClientNotFound, InvalidTransferAmount
	{
		Account currentAccount = dependency.getAccountAsObject();
		Account targetAccount = bank.getAccount(target);
		
		targetAccount.transfer(currentAccount, amount);
	}

}
