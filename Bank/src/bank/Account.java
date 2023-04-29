package bank;

public class Account extends Bank{
	public static int a_id = 0;
	protected final int id;
	protected double balance;
	protected double max_withdrawal;
	
	public Account(Bank b) {
		super();
		this.id = ++a_id;
		this.balance = 0;
		this.max_withdrawal = 1000;
		b.addConto(this);
	}
	
	public Account(double balance, Bank b) {
		super();
		this.id = ++a_id;
		this.balance = balance;
		this.max_withdrawal = balance * 0.1;
		b.addConto(this);
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

	public double getBalance() {
		return balance;
	}

	// balance can be negative
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMax_withdrawal() {
		return max_withdrawal;
	}

	public void setMax_withdrawal(double max_withdrawal) {
		if(max_withdrawal > (this.getBalance()*0.1)) {
			System.out.println("Max withdrawal to high, maximum value allowed is 10% of the current balance");
			System.out.println("Max withdrawal not updated");
		}
		else {
			this.max_withdrawal = max_withdrawal;
		}
	}

	public int getId() {
		return id;
	}

	
	@Override
	public String toString() {
		return id + " Conto Corrente - saldo = " + balance;
	}
	
	public Boolean equals(Account a) {
		return (this.id == a.id);
	}
	
	
}
