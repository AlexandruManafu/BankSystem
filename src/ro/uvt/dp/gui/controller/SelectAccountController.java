package ro.uvt.dp.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import ro.uvt.dp.exceptions.AccountNotEmpty;
import ro.uvt.dp.exceptions.AccountNotFound;
import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.gui.model.SelectAccountModel;
import ro.uvt.dp.gui.view.SelectAccountPanel;

public class SelectAccountController {
	
	private SelectAccountPanel view;
	private SelectAccountModel model;

	public SelectAccountController(SelectAccountPanel view, SelectAccountModel model) {
		
		this.view = view;
		this.model = model;
		
		addActionListeners();
	}
	
	private void addActionListeners()
	{
		ClientConfirmListener listener = new ClientConfirmListener();
		view.addConfirmListener(listener);
		
		AccountSelectListener listener2 = new AccountSelectListener();
		view.addSelectListener(listener2);
		
		AccountDeleteListener listener3 = new AccountDeleteListener();
		view.addDeleteAccountListener(listener3);
	}
	
	public void showBalance() throws AccountNotFound, ClientNotFound
	{
		String iban = (String) view.getAccountSelect().getSelectedItem();
		if(iban!=null)
		{
			System.out.print(iban);
			double balance = model.getBalance();
			view.toggleBalance(balance, true);
		}
	}
	
	class ClientConfirmListener implements ActionListener{
		
		private void displayAccountSelect()
		{
			view.toggleAccountSelect(true);
			String[] accounts = model.getAccountsNames();
			view.setAccountsForClient(accounts);
			
			view.setFeedback("");
		}
		
		private void displayError()
		{
			view.resetAccountSelect();
			view.toggleAccountSelect(false);
			view.setFeedback("Client not found");
			
			view.toggleBalance(0, false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {			
			JTextField clientInput = view.getClientInput();
			String clientName = clientInput.getText();
			
			if(model.login(clientName))
				displayAccountSelect();
			else
				displayError();
			
		}
		
	}
	
	class AccountSelectListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String selectedOption = (String) view.getAccountSelect().getSelectedItem();
				model.setCurrentAccount(selectedOption);
				showBalance();
			} catch (AccountNotFound | ClientNotFound e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	class AccountDeleteListener implements ActionListener{
		
		private void refreshSelection()
		{
			String[] accounts = model.getAccountsNames();
			view.setAccountsForClient(accounts);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JComboBox<String> accountSelect = view.getAccountSelect();
				String selectedOption = (String) accountSelect.getSelectedItem();
				model.deleteAccount(selectedOption);
				refreshSelection();
				
				view.setFeedback("Account "+ selectedOption + " deleted.");
				model.printBank();
			} catch (NullPointerException | AccountNotFound | ClientNotFound  e1) {
				view.setFeedback("No account selected.");
			}
			catch(AccountNotEmpty e1)
			{
				view.setFeedback("Only empty accounts can be deleted");
			}
			
		}
		
	}


}
