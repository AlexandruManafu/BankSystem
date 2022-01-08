package ro.uvt.dp.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.exceptions.ClientsLimitExceeded;
import ro.uvt.dp.exceptions.InvalidTransferAmount;
import ro.uvt.dp.gui.model.*;
import ro.uvt.dp.gui.view.*;

public class DeposeRetrieveController {
	
	private DeposeRetrievePanel view;
	private DeposeRetrieveModel model;
	
	
	public DeposeRetrieveController(DeposeRetrievePanel view, DeposeRetrieveModel model) {
		
		this.view = view;
		this.model = model;
		
		addActionListeners();
	}
	
	public DeposeRetrievePanel getView()
	{
		return this.view;
	}
	
	
	private void addActionListeners()
	{
		ConfirmListener listener = new ConfirmListener();
		view.addConfirmListener(listener);
	}
	
	class ConfirmListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(model.accountSelected())
			{
				String action = (String) view.getActionSelect().getSelectedItem();
				
				try {
				double amount = Double.parseDouble(view.getAmountField().getText());
				model.doAction(action, amount);
				view.setFeedback(""+action +" succesfull");
				
				}catch(NumberFormatException e1)
				{
					view.setFeedback("The Amount must be a number.");
				}
				catch(InvalidTransferAmount e1)
				{
					if(action.equals("Retrieve"))
						view.setFeedback("Invalid Retrieve Amount");
					else
						view.setFeedback("The amount must be positive");
				}
				model.printBank();
			}
			else
			{
				view.setFeedback("No Account was selected.");
			}
		}
		
	}
	
	

}
