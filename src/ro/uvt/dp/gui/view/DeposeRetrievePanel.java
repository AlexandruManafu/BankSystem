package ro.uvt.dp.gui.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ro.uvt.dp.account.Account;

public class DeposeRetrievePanel extends JPanel {
	
	private JLabel actionLabel;
	private JComboBox<String> actionSelect;
	private JLabel amountLabel;
	private JTextField amountField;
	
	private JButton confirmClient;
	private JLabel feedbackLabel;
	
	private String actions[] = {"Depose", "Retrieve"};
	
	public DeposeRetrievePanel()
	{
		String label = "Depose / Retrieve";
		this.add( new JLabel(label) );
		this.setName(label);
		this.setLayout(null);
		
		actionLabel = new JLabel("Action:");
		actionLabel.setBounds(70, 20, 80, 25);
		
		actionSelect = new JComboBox<String>(actions);
		actionSelect.setBounds(130, 20,90,25);
		
		amountLabel = new JLabel("Amount:");
		amountLabel.setBounds(70, 60, 120, 25);
		
		amountField = new JTextField();
		amountField.setBounds(130, 60, 140, 25);
		
		confirmClient = new JButton("Confirm");
		confirmClient.setBounds(150,100,80,30);
		
		
		feedbackLabel = new JLabel("");
		feedbackLabel.setBounds(120, 140, 200, 25);
		
				
		addComponents();
	}
	
	private void addComponents()
	{
		this.add(actionLabel);
		this.add(actionSelect);
		this.add(amountLabel);
		this.add(amountField);
		this.add(confirmClient);
		this.add(feedbackLabel);
	}
	
	public void addConfirmListener(ActionListener listener)
	{
		confirmClient.addActionListener(listener);
	}
	
	public void addSelectListener(ActionListener listener)
	{
		actionSelect.addActionListener(listener);
	}
	
	public JComboBox<String> getActionSelect()
	{
		return actionSelect;
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
