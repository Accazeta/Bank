package bank;

import java.util.ArrayList;

public class Bank implements Visitable {
	public final String nome;
	public final long codiceFiscale;
	private ArrayList<Customer> clients;
	private ArrayList<Transaction> movimenti;
	private ArrayList<Account> contiAperti;

	Bank() {
		this.nome = "Banca di Bergamo";
		this.codiceFiscale = (int) ((Math.random() * (9999999 - 1000000)) + 1000000);
		this.clients = new ArrayList<Customer>();
		this.movimenti = new ArrayList<Transaction>();
		this.contiAperti = new ArrayList<Account>();
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

	@Override
	public String toString() {
		return this.nome;
	}

	public void addCustomer(Customer... args) {
		for (Customer c : args) {
			this.clients.add(c);
		}
	}

	public ArrayList<Customer> getCustomers() {
		return this.clients;
	}

	public void addTransaction(Transaction... args) {
		for (Transaction t : args) {
			this.movimenti.add(t);
		}
	}

	public void addConto(Account... args) {
		for (Account a : args) {
			this.contiAperti.add(a);
		}
	}

	public ArrayList<Account> getConti() {
		return this.contiAperti;
	}

	public Account getSingleConto(int id) {
		for (Account c : this.getConti()) {
			if (c.id == id) {
				return c;
			}
		}
		return null;
	}

	public Customer getSingleCustomer(int id) {
		for (Customer c : this.clients) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	public Customer findOwnerGivenAccount(Account a) {
		for (Customer c : this.getCustomers()) {
			for (Account temp : c.getConti()) {
				if (temp.equals(a)) {
					return c;
				}
			}
		}
		return null;
	}

	public Boolean equals(Bank b) {
		return (this.nome.equals(b.nome) && this.codiceFiscale == b.codiceFiscale);
	}
}
