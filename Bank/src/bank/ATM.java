package bank;

import java.util.Scanner;

public class ATM implements Visitor<Object> {

	@Override
	public Double visit(Account a) {
		return a.balance;
	}

	@Override
	public String visit(Customer c) {
		return c.nome;
	}

	@Override
	public Double visit(DepositAccount da) {
		return da.interest;
	}

	@Override
	public String visit(Bank bank) {
		return bank.nome;
	}
	
	public static String inputATM(String testo, String opzioniValide[]) {
		System.out.print(testo);
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		for(String temp : opzioniValide) {
			if(temp.equals(s)) {
				return s; // Se l'input utente è valido, lo resituisco
			}
		}
		// Se l'input non è tra le opzioni valide, stampo un messaggio di errore e resituisco
		// la stringa vuota
		System.out.println("Attenzione, " + s + " non è un valore valido");
		System.out.print("Le opzioni ammesse sono: ");
		for(String val : opzioniValide) {
			System.out.print(val + " ");
		} 
		System.out.println();
		return "";
	}

	public static String inputATM(String testo, double min, double max) {
		System.out.print(testo);
		Scanner scan = new Scanner(System.in);
		double value = scan.nextDouble();
		if(value < min || value > max) {
			System.out.println("Attenzione, " + value + " non è un valore valido");
			System.out.println("L'intervallo ammesso è: [" + min + ", " + max + "]" );
			System.out.println();
			return "";
		}
		else {
			System.out.println();
			return String.valueOf(value);
		}
	}
	
	public static void main(String[] args) {
		Bank my_bank = new Bank();
		Customer utente = new Customer("Federico", "Panzeri");
		my_bank.addCustomer(utente);

		Scanner in = new Scanner(System.in);
		String s = "";
		
		my_bank.accept(new ATM());
		
		try {
			while (!s.equals("0")) {
				System.out.println("Benvenuto" + utente.getName() + ". Operazioni disponibili: ");
				System.out.println("1 - Preleva");
				System.out.println("2 - Deposita");
				System.out.println("3 - Saldo");
				System.out.println("4 - Apri conto deposito");
				System.out.println("5 - Profilo");
				System.out.println("0 - esci");
				System.out.println("---------------------------------");
				System.out.print("Scelta: ");
				s = in.nextLine();

				switch (s) {
				case "1": {
					System.out.println("Selezionare il conto: ");
					String opzValide[] = new String[utente.getConti().size() + 1];
					for (Account conto : utente.getConti()) {
						System.out.println(conto.toString());
						opzValide[conto.getId()-1] = String.valueOf(conto.getId()); 
					}
					opzValide[opzValide.length - 1] = "0";
					System.out.println("---------------------------------");
					while (!s.equals("0")) {
						s = inputATM("(0 per uscire) Valore: ", opzValide);
						if(s.equals("")) {
							continue;
						}
						else {
							System.out.println("Inserisci l'importo da prelevare (usa il punto per i decimali)");
							System.out.println("---------------------------------");
							s = inputATM("(0 per uscire) Valore: ", 0, utente.getConti().get(Integer.valueOf(s)-1).max_withdrawal);
						}
					}
				}
				case "2": {

				}
				case "3": {

				}
				case "4": {

				}
				case "5": {

				}
				case "0": {
					System.out.println("Grazie per aver usato il nostro ATM!");
					break;
				}
				default:
					System.out.println("Input errato. Ricarico... ");
					Thread.sleep(3000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
