package ro.uvt.dp.gui.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ro.uvt.dp.account.Account;

public class SelectAccountPanel extends JPanel {
	
	private JLabel clientLabel;
	private JTextField clientField;
	private JButton confirmClient;
	private JLabel selectAccountLabel;
	private JComboBox<String> selectAccount;
	private JButton deleteAccountButton;
	private JLabel balanceLabel;
	private JLabel feedbackLabel;
	
	
	private String[] accountsForClient = new String[0];
	
	public SelectAccountPanel()
	{
		String label = "Select Account";
		this.add( new JLabel(label) );
		this.setName(label);
		this.setLayout(null);
		
		clientLabel = new JLabel("Client:");
		clientLabel.setBounds(70, 20, 80, 25);
		
		clientField = new JTextField(20);
		clientField.setBounds(120,20,165,25);
		
		confirmClient = new JButton("Log-In / Refresh");
		confirmClient.setBounds(300,16,160,30);
		
		selectAccountLabel = new JLabel("Select Account:");
		selectAccountLabel.setBounds(70, 70, 120, 25);
		
		balanceLabel = new JLabel("");
		balanceLabel.setBounds(70, 100, 200, 25);
		
		selectAccount = new JComboBox<String>(accountsForClient);
		selectAccount.setBounds(180, 70, 120, 25);
		
		deleteAccountButton = new JButton("Delete Selected Account");
		deleteAccountButton.setBounds(150,130, 180, 25);
		
		feedbackLabel = new JLabel("");
		feedbackLabel.setBounds(150, 160, 300, 25);
		
		toggleAccountSelect(false);
				
		addComponents();
	}
	
	private void addComponents()
	{
		this.add(selectAccountLabel);
		this.add(selectAccount);
		this.add(clientLabel);
		this.add(clientField);
		this.add(confirmClient);
		this.add(feedbackLabel);
		this.add(balanceLabel);
		this.add(deleteAccountButton);
	}
	
	public void toggleAccountSelect(boolean display)
	{
		selectAccount.setVisible(display);
		selectAccountLabel.setVisible(display);
		deleteAccountButton.setVisible(display);
		selectAccount.repaint();
		selectAccountLabel.repaint();
		deleteAccountButton.repaint();
	}
	
	public void toggleBalance(double balance, boolean display)
	{
		String message = "Current Balance:  " + balance;
		balanceLabel.setText(message);
		balanceLabel.setVisible(display);
		balanceLabel.repaint();
	}
	
	public void addConfirmListener(ActionListener listener)
	{
		confirmClient.addActionListener(listener);
	}
	
	public void addSelectListener(ActionListener listener)
	{
		selectAccount.addActionListener(listener);
	}
	
	public void addDeleteAccountListener(ActionListener listener)
	{
		deleteAccountButton.addActionListener(listener);
	}
	
	public JTextField getClientInput()
	{
		return clientField;
	}
	public JComboBox<String> getAccountSelect()
	{
		return selectAccount;
	}
	
	public void setAccountsForClient(String[] accounts)
	{
		resetAccountSelect();
		 
		this.accountsForClient = accounts;
		for(int i = 0; i<accountsForClient.length;i++)
		{
			selectAccount.addItem(accountsForClient[i]);
		}
		selectAccount.repaint();
	}
	
	public void resetAccountSelect()
	{
		for(int i = 0; i<accountsForClient.length;i++)
		{
			selectAccount.removeItem(accountsForClient[i]);
		}
		selectAccount.repaint();
	}
	
	public void setFeedback(String message)
	{
		feedbackLabel.setText(message);
	}

}
