package ro.uvt.dp.gui.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ro.uvt.dp.account.Account;

public class TransferPanel extends JPanel {
	
	private JLabel targetLabel;
	private JTextField targetField;
	private JLabel amountLabel;
	private JTextField amountField;
	
	private JButton confirmClient;
	private JLabel feedbackLabel;
	public TransferPanel()
	{
		String label = "Transfer";
		this.add( new JLabel(label) );
		this.setName(label);
		this.setLayout(null);
		
		targetLabel = new JLabel("Send to: ");
		targetLabel.setBounds(70, 20, 80, 25);
		
		targetField = new JTextField();
		targetField.setBounds(130, 20,140,25);
		
		amountLabel = new JLabel("Amount:");
		amountLabel.setBounds(70, 60, 120, 25);
		
		amountField = new JTextField();
		amountField.setBounds(130, 60, 120, 25);
		
		confirmClient = new JButton("Confirm");
		confirmClient.setBounds(150,100,80,30);
		
		
		feedbackLabel = new JLabel("");
		feedbackLabel.setBounds(120, 140, 200, 25);
		
				
		addComponents();
	}
	
	private void addComponents()
	{
		this.add(targetLabel);
		this.add(targetField);
		this.add(amountLabel);
		this.add(amountField);
		this.add(confirmClient);
		this.add(feedbackLabel);
	}
	
	public void addConfirmListener(ActionListener listener)
	{
		confirmClient.addActionListener(listener);
	}
	
	public JTextField getTargetField()
	{
		return targetField;
	}
	
	public JTextField getAmountField()
	{
		return amountField;
	}
	
	public void setFeedback(String message)
	{
		feedbackLabel.setText(message);
	}

}
