package bank;

public class Account extends Bank{
	public static int a_id = 0;
	private int id;
	private double balance;
	private double max_withdrawal;
	
	public Account() {
		super();
		this.id = ++a_id;
		this.balance = 0;
		this.max_withdrawal = 1000;
	}
	
	public Account(double balance) {
		super();
		this.id = ++a_id;
		this.balance = balance;
		this.max_withdrawal = balance * 0.1;
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
