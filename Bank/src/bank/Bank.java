package bank;

public class Bank implements Visitable{
	public final String nome;
	public final double codiceFiscale;
	
	Bank() {
		this.nome = "Banca di Bergamo";
		this.codiceFiscale = (int) ((Math.random() * (9999999 - 1000000)) + 1000000);
	}
	
	@Override
	public <T> T accept(Visitor<T> v) {
		return v.visit(this);
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
