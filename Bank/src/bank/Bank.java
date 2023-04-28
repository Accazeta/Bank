package bank;

import java.util.ArrayList;

public class Bank implements Visitable{
	public final String nome;
	public final double codiceFiscale;
	private ArrayList<Customer> clients;
	private ArrayList<Transaction> movimenti;
	
	Bank() {
		this.nome = "Banca di Bergamo";
		this.codiceFiscale = (int) ((Math.random() * (9999999 - 1000000)) + 1000000);
		this.clients = new ArrayList<Customer>();
		this.movimenti = new ArrayList<Transaction>();
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
	public void addCustomer(Customer c) {
		this.clients.add(c);
	}
	
	public void addTransaction(Transaction t) {
		this.movimenti.add(t);
	}

}
