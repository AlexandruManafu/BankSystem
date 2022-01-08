package ro.uvt.dp.gui.view;

import java.awt.event.ActionListener;

import javax.swing.*;

public class NewClientPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel actionLabel;
	private String actions[] = {"New Client", "New Account"};
	private JComboBox<String> actionSelect;
	
	private JLabel typeLabel;
	private String accountTypes[] = {"RON", "EUR"};
	private JComboBox<String> typeSelect;
	
	private JLabel clientLabel;
	private JTextField clientField;
	private JButton confirm;
	
	private JLabel confirmLabel;
	
	public NewClientPanel()
	{
		String label = "New Client / Account";
		
		this.add( new JLabel(label) );
		this.setName(label);
		this.setLayout(null);
		
		actionLabel = new JLabel("Action:");
		actionLabel.setBounds(70, 20, 80, 25);
		
		actionSelect = new JComboBox<String>(actions);
		actionSelect.setBounds(120, 20, 100, 25);
		
		typeLabel = new JLabel("Account Type:");
		typeLabel.setBounds(250, 20, 80, 25);
		
		typeSelect = new JComboBox<String>(accountTypes);
		typeSelect.setBounds(350, 20, 100, 25);
		
		clientLabel = new JLabel("Client:");
		clientLabel.setBounds(70, 60, 80, 25);
		
		clientField = new JTextField(20);
		clientField.setBounds(120,60,165,25);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(150,100,80,30);
		
		confirmLabel = new JLabel("");
		confirmLabel.setBounds(130, 150, 300, 25);
		
		addComponents();
	}
	
	private void addComponents()
	{
		this.add(actionLabel);
		this.add(actionSelect);
		this.add(typeLabel);
		this.add(typeSelect);
		this.add(clientLabel);
		this.add(clientField);
		this.add(confirm);
		this.add(confirmLabel);
		
		toggleAccountType(false);
	}
	
	public void toggleAccountType(Boolean display)
	{
		typeLabel.setVisible(display);
		typeSelect.setVisible(display);
		typeLabel.repaint();
		typeSelect.repaint();
	}
	
	public String getAction()
	{
		return (String) this.actionSelect.getSelectedItem();
	}
	
	public String getInputName()
	{
		return (String) this.clientField.getText();
	}
	
	public String getType()
	{
		return (String) this.typeSelect.getSelectedItem();
	}
		
	public JButton getConfirmButton()
	{
		return confirm;
	}
	
	public JComboBox<String> getActionSelect()
	{
		return actionSelect;
	}
	
	public JComboBox<String> getTypeSelect()
	{
		return typeSelect;
	}
	
	public JLabel getConfirmFeedback()
	{
		return confirmLabel;
	}
	
	public void addConfirmListener(ActionListener listener)
	{
		confirm.addActionListener(listener);
	}
	
	public void addSelectListener(ActionListener listener)
	{
		actionSelect.addActionListener(listener);
		typeSelect.addActionListener(listener);
	}
	
}
