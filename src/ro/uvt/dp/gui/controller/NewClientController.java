package ro.uvt.dp.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.exceptions.ClientsLimitExceeded;
import ro.uvt.dp.gui.model.*;
import ro.uvt.dp.gui.view.*;

public class NewClientController {
	
	private NewClientPanel newClientPanel;
	private NewClientModel newClientModel;
	
	
	public NewClientController(NewClientPanel newClientPanel, NewClientModel newClientModel) {
		
		this.newClientPanel = newClientPanel;
		this.newClientModel = newClientModel;
		
		addActionListeners();
	}
	
	public NewClientPanel getView()
	{
		return this.newClientPanel;
	}
	
	
	private void addActionListeners()
	{
		onClickCofirm listener = new onClickCofirm();
		newClientPanel.addConfirmListener(listener);
		
		onActionSelect listeners = new onActionSelect();
		newClientPanel.addSelectListener(listeners);
	}
	
	/**
	 * Create a new client or create a new account for the input name client
	 * The account name will be generated automatically
	 */
	private class onClickCofirm implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton source = newClientPanel.getConfirmButton();
			
			String name = newClientPanel.getInputName();
			String action = newClientPanel.getAction();
			String type = newClientPanel.getType();
			JLabel confirmLabel = newClientPanel.getConfirmFeedback();
				
			if(name.isEmpty())
			{
				confirmLabel.setText("Client name cannot be empty");
				confirmLabel.repaint();
				return;
			}
				
			if(action == "New Client")
			{
				confirmLabel.setText("Created New Client: " + name);
				confirmLabel.repaint();
			}
			else if(action == "New Account")
			{
				confirmLabel.setText("Created New Account for: " + name);
				confirmLabel.repaint();
			}
			
			try {
				
				newClientModel.performAction(action, name, type);
				
			} catch (ClientsLimitExceeded | ClientNotFound e1) {
				if(e1 instanceof ClientsLimitExceeded)
				{
					confirmLabel.setText("The limit of clients and accounts is 5");
					confirmLabel.repaint();
				}
				else if(e1 instanceof ClientNotFound)
				{
					confirmLabel.setText("Target Client not found");
					confirmLabel.repaint();
				}
			}
			System.out.println(newClientModel.getBank());
		}	
	}
	
	/**
	 * If the action is 'new account' allow the user to select the type of the account.
	 * 
	 */
	private class onActionSelect implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JComboBox<String> actionSelect = newClientPanel.getActionSelect();
			JComboBox<String> typeSelect = newClientPanel.getTypeSelect();
			String action = newClientPanel.getAction();
			
			if(e.getSource() == actionSelect)
			{
				String selected = (String) actionSelect.getSelectedItem();
				action = selected;
				if(action.equals("New Account"))
				{
					newClientPanel.toggleAccountType(true);
				}
				else
				{
					newClientPanel.toggleAccountType(false);
				}
			}	
		}	
	}
	
	

}
