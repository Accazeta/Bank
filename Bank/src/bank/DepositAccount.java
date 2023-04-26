package bank;

public class DepositAccount extends Account{
	private double interest;
	
	public DepositAccount() {
		super();
		this.interest = 0.03;
	}
	
	public DepositAccount(double balance, double interest) {
		super(balance);
		this.interest = interest;
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
	
	@Override
	public double getBalance() {
		return balance;
	}

	// balance can be negative
	@Override
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public double getMax_withdrawal() {
		return max_withdrawal;
	}

	@Override
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

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getInterest() {
		return this.interest;
	}

	@Override
	public String toString() {
		return "DepositAccount [interest=" + interest + ", id=" + id + "]";
	}
	
	
	
}
