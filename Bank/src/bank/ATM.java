package bank;

public class ATM implements Visitor<Object>{

	@Override
	public Integer visit(Account a) {
		return null;
	}

	@Override
	public String visit(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer visit(DepositAccount da) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Bank bank) {
		return null;
	}

}
