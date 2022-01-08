package ro.uvt.dp.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ro.uvt.dp.exceptions.AccountNotFound;
import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.exceptions.ClientsLimitExceeded;
import ro.uvt.dp.exceptions.InvalidTransferAmount;
import ro.uvt.dp.gui.model.*;
import ro.uvt.dp.gui.view.*;

public class TransferController {
	
	private TransferPanel view;
	private TransferModel model;
	
	
	public TransferController(TransferPanel view, TransferModel model) {
		
		this.view = view;
		this.model = model;
		
		addActionListeners();
	}
	
	public TransferPanel getView()
	{
		return this.view;
	}
	
	
	private void addActionListeners()
	{
		ConfirmListener listener = new ConfirmListener();
		view.addConfirmListener(listener);		
	}
	
	public class ConfirmListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				double balance = model.getBalance();
				double amount = Double.parseDouble(view.getAmountField().getText());
				String target = view.getTargetField().getText();
				model.transfer(target, amount);
				view.setFeedback("Transfer to "+ target + " success.");
			} 
			catch (ClientNotFound e1) {
				view.setFeedback("No Account Selected from which to transfer.");
			}
			catch (AccountNotFound e1) {
				view.setFeedback("Target Account not found.");
				e1.printStackTrace();
			}
			catch (InvalidTransferAmount e1){
				view.setFeedback("Invalid Amount for transfer.");
			}
			catch (NumberFormatException e1){
				view.setFeedback("The Amount must be a number.");
			}
			
		}
		
	}
	
	

}
