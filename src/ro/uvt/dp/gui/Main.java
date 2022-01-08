package ro.uvt.dp.gui;
import ro.uvt.dp.bank.Bank;
import ro.uvt.dp.gui.controller.*;
import ro.uvt.dp.gui.model.*;
import ro.uvt.dp.gui.view.*;


import javax.swing.*;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane TabsContainer = new JTabbedPane();
	private NewClientPanel first;
	private SelectAccountPanel second;
	private DeposeRetrievePanel third;
	private TransferPanel fourth;
	
	private SelectAccountModel selectAccountModel;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main nav = new Main();
		nav.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nav.setSize(500,400);
		nav.setVisible(true);
		
	}
	
	Main()
	{
		Bank bank = Bank.getBank("Bank5848");
		createFirstTab(bank);
		createSecondTab(bank);
		createThirdTab(selectAccountModel);
		createFourthTab(selectAccountModel);
		
		addTabs();
	}
	
	private void addTabs()
	{
		TabsContainer.add(first);
		TabsContainer.add(second);
		TabsContainer.add(third);
		TabsContainer.add(fourth);
		add(TabsContainer);
	}
	
	private void createFirstTab(Bank bank)
	{
		NewClientModel model = new NewClientModel(bank);
		NewClientPanel view = new NewClientPanel();
		NewClientController controller = new NewClientController(view, model);
		first = view;
	}
	
	private void createSecondTab(Bank bank)
	{
		selectAccountModel = new SelectAccountModel(bank);
		SelectAccountPanel view = new SelectAccountPanel();
		SelectAccountController controller = new SelectAccountController(view, selectAccountModel);
		second = view;
	}
	
	private void createThirdTab(SelectAccountModel dependency)
	{
		DeposeRetrieveModel model = new DeposeRetrieveModel(dependency);
		DeposeRetrievePanel view = new DeposeRetrievePanel();
		DeposeRetrieveController controller = new DeposeRetrieveController(view, model);
		third = view;
	}
	
	private void createFourthTab(SelectAccountModel dependency)
	{
		TransferModel model = new TransferModel(dependency);
		TransferPanel view = new TransferPanel();
		TransferController controller = new TransferController(view, model);
		fourth = view;
	}
	
	


}
