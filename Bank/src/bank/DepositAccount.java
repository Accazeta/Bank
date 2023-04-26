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
}
