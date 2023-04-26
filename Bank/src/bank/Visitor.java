package bank;

public interface Visitor<T> {
	T visit(Account a);
	T visit(Customer c);
	T visit(DepositAccount da);
	T visit(Bank bank);
}
