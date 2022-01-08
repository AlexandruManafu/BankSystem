package ro.uvt.dp.bank;

import java.util.ArrayList;

import ro.uvt.dp.client.Client;
import ro.uvt.dp.exceptions.ClientNotFound;
import ro.uvt.dp.exceptions.ClientsLimitExceeded;

// Singleton pattern
public class Bank {

	private final static int MAX_CLIENTS_NUMBER = 100;
	private ArrayList<Client> clients;
	private String bankCode = null;
	
	private static Bank instance = null;

	protected Bank(String codeBank) {
		this.bankCode = codeBank;
		clients = new ArrayList<Client>();
	}
	
	public static Bank getBank(String codeBank)
	{
		if(instance==null)
		{
			instance = new Bank(codeBank);
		}
		return instance;
			
	}
	/**
	 * Add the argument to the clients list of the bank
	 * @param c - client object
	 */
	public void addClient(Client c) throws ClientsLimitExceeded {
		if(clients.size() < MAX_CLIENTS_NUMBER)
			clients.add(c);
		else
			throw new ClientsLimitExceeded
			("Error, Bank addClient method over the limit of MAX_CLIENTS_NUMBER");
	}

	/**
	 * Search a client in bank client list by name.
	 * @param nume - the client exact name
	 * @return the client, if does not exists it return null.
	 */
	public Client getClient(String nume) throws ClientNotFound {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getName().equals(nume)) {
				return clients.get(i);
			}
		}
		throw new ClientNotFound("Client not found for the get operation");
	}
	
	public Client deleteClient(String nume) throws ClientNotFound {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getName().equals(nume)) {
				clients.remove(i);
			}
		}
		throw new ClientNotFound("Client not found for the delete operation");
	}
	@Override
	public String toString() {
		return "Bank [code=" + bankCode + ", clients=" + clients.toString() + "]";
	}

}
