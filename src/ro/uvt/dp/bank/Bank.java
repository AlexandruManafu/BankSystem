package ro.uvt.dp.bank;

import java.util.ArrayList;

import ro.uvt.dp.client.Client;

public class Bank {

	private final static int MAX_CLIENTS_NUMBER = 100;
	private ArrayList<Client> clients;
	private String bankCode = null;

	public Bank(String codBanca) {
		this.bankCode = codBanca;
		clients = new ArrayList<Client>();
	}
	/**
	 * Add the argument to the clients list of the bank
	 * @param c - client object
	 */
	public void addClient(Client c) {
		if(clients.size() < MAX_CLIENTS_NUMBER)
			clients.add(c);
		else
			System.out.println("Error, Bank addClient method over the limit of MAX_CLIENTS_NUMBER");
	}

	/**
	 * Search a client in bank client list by name.
	 * @param nume - the client exact name
	 * @return the client, if does not exists it return null.
	 */
	public Client getClient(String nume) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getName().equals(nume)) {
				return clients.get(i);
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "Bank [code=" + bankCode + ", clients=" + clients.toString() + "]";
	}

}
