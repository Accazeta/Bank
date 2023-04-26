package bank;

public class Bank implements Visitable{
	String nome;
	double codiceFiscale;
	
	Bank() {
		this.nome = "Banca di Bergamo";
		this.codiceFiscale = (int) ((Math.random() * (9999999 - 1000000)) + 1000000);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

}
