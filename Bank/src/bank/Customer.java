package bank;

import java.util.ArrayList;

public class Customer extends Bank{
	public static int c_id=0;
	private final int id;
	private String name;
	private String surname;
	private ArrayList<Account> conti_personali;
	private ArrayList<Transaction> movimenti;
	
	public Customer(Bank b) {
		super();
		this.name = "";
		this.surname = "";
		this.id = ++c_id;
		this.conti_personali = new ArrayList<Account>();
		this.conti_personali.add(new Account(b));
		b.addCustomer(this);
		this.movimenti = new ArrayList<Transaction>();
	}
	
	public Customer(String name, String surname, Bank b) {
		super();
		this.name = name;
		this.surname = surname;
		this.id = ++c_id;
		this.conti_personali = new ArrayList<Account>();
		this.conti_personali.add(new Account(b));
		b.addCustomer(this);
		this.movimenti = new ArrayList<Transaction>();
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public ArrayList<Account> getConti() {
		return this.conti_personali;
	}
	
	public Account getSingleConto(int id) {
		return this.conti_personali.get(id);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return this.movimenti;
	}
	
	public void addTransaction(Transaction t) {
		this.movimenti.add(t);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
	
	
}
