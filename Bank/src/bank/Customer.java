package bank;

public class Customer extends Bank{
	public static int c_id=0;
	private int id;
	private String name;
	private String surname;
	
	public Customer() {
		super();
		this.name = "";
		this.surname = "";
		this.id = ++c_id;
	}
	
	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
		this.id = ++c_id;
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
