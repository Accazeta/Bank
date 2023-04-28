package bank;

public class Transaction extends Bank{
	Customer source;
	Customer destination;
	Double amount;
	
	public Transaction(Customer source, Customer destination, Double amount) {
		this.source = source;
		this.destination = destination;
		this.amount = amount;
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}
