package bank;

import java.util.ArrayList;

public class Customer extends Bank{
	public static int c_id=0;
	private final int id;
	private String name;
	private String surname;
	private ArrayList<Account> conti;
	
	public Customer() {
		super();
		this.name = "";
		this.surname = "";
		this.id = ++c_id;
		this.conti = new ArrayList<Account>();
		this.conti.add(new Account());
	}
	
	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
		this.id = ++c_id;
		this.conti = new ArrayList<Account>();
		this.conti.add(new Account());
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
		return this.conti;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
	
	
}
