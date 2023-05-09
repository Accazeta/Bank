package bank;

public record Transaction(Account source, Account destination, Double amount, Bank b) implements Visitable {
	public Transaction {
		b.addTransaction(this);
	}

	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}
}

/*
 * public class Transaction extends Bank{ Account source; Account destination;
 * Double amount;
 * 
 * public Transaction(Account source, Account destination, Double amount, Bank
 * b) { this.source = source; this.destination = destination; this.amount =
 * amount; b.addTransaction(this); }
 * 
 * @Override public <T> T accept(Visitor<T> v) { return v.visit(this); } }
 */
