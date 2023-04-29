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

	@Override
	public Double visit(Transaction tr) {
		return tr.amount;
	}

	public static String inputATM(String testo, String opzioniValide[]) {
		System.out.print(testo);
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		for (String temp : opzioniValide) {
			if (temp.equals(s)) {
				return s; // Se l'input utente è valido, lo resituisco
			}
		}
		// Se l'input non è tra le opzioni valide, stampo un messaggio di errore e
		// resituisco
		// la stringa vuota
		System.out.println("Attenzione, " + s + " non è un valore valido");
		System.out.print("Le opzioni ammesse sono: ");
		for (String val : opzioniValide) {
			System.out.print(val + " ");
		}
		System.out.println();
		return "";
	}

	public static String inputATM(String testo, double min, Account conto) {
		System.out.print(testo);
		Scanner scan = new Scanner(System.in);
		String s_value = scan.nextLine();
		try {
			Double value = Double.parseDouble(s_value);
			if (value < min || value > Math.min(conto.max_withdrawal, conto.balance)) {
				if ((Double) (conto.accept(new ATM())) < 10 && value != 0) {
					System.out.println("Fondi insufficienti");
				} else {
					if (value != 0) {
						System.out.println("Attenzione, " + value + " non è un valore valido");
						System.out.println("L'intervallo ammesso è: [" + min + ", "
								+ Math.min(conto.max_withdrawal, (Double) (conto.accept(new ATM()))) + "]");
					} else {
						System.out.println();
						return "0";
					}

				}
				System.out.println();
				return "";
			} else {
				System.out.println();
				return String.valueOf(value);
			}
		} catch (Exception e) {
			System.out.println("Caratteri non validi");
			return "";
		}

	}

	public static void main(String[] args) {
		Bank my_bank = new Bank();
		Customer utente = new Customer("Federico", "Panzeri", my_bank);
		Customer utente2 = new Customer("Acram", "Bousaid", my_bank);
		//my_bank.addCustomer(utente);
		//my_bank.addConto(utente.getConti().get(utente.getConti().size() - 1));

		Scanner in = new Scanner(System.in);
		String s = "";

		try {
			/*
			System.out.println("Attesa tessera...");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			Boolean pin = false;
			while (!pin) {
				System.out.print("Inserire PIN: ");
				s = in.nextLine();
				if (s.equals("1234")) {
					System.out.print("Benvenuto " + utente.getName()+". ");
					pin = true;
				} else {
					System.out.println("Pin errato!");
					pin = false;
				} 
			}//*/
			while (!s.equals("0")) {
				System.out.println("Operazioni disponibili: ");
				System.out.println(Constants.ANSI_PINK + "1 - Preleva" + Constants.ANSI_RESET);
				System.out.println(Constants.ANSI_GREEN + "2 - Deposita" + Constants.ANSI_RESET);
				System.out.println(Constants.ANSI_PURPLE + "3 - Bonifico" + Constants.ANSI_RESET);
				System.out.println(Constants.ANSI_RED + "4 - Apri conto deposito" + Constants.ANSI_RESET);
				System.out.println(Constants.ANSI_YELLOW + "5 - Profilo" + Constants.ANSI_RESET);
				System.out.println(Constants.ANSI_CYAN + "0 - esci" + Constants.ANSI_RESET);
				System.out.println("---------------------------------");
				System.out.print("Scelta: ");
				s = in.nextLine();

				switch (s) {
				case "1": {
					while (!s.equals("0")) {
						boolean flag = false; // flag usato per deteminare se l'operazione è andata a buon fine
						System.out.println(Constants.ANSI_PINK + "\nSelezionare il conto: ");
						String opzValide[] = new String[utente.getConti().size() + 1];
						for (Account conto : utente.getConti()) {
							System.out.println(conto.toString());
							opzValide[conto.getId() - 1] = String.valueOf(conto.getId());
						}
						opzValide[opzValide.length - 1] = "0"; // Add zero to valid options
						System.out.println(Constants.ANSI_RESET + "---------------------------------");
						s = inputATM("(0 per uscire) Valore: ", opzValide);
						if (s.equals("")) {
							continue;
						}
						if (s.equals("0")) {
							break;
						} else {
							final int accountNumber = Integer.valueOf(s) - 1;
							Account conto = utente.getConti().get(accountNumber);
							while (!s.equals("0")) {
								System.out.println(Constants.ANSI_PINK
										+ "\nInserisci l'importo da prelevare (usa il punto per i decimali)");
								System.out.println(Constants.ANSI_RESET + "---------------------------------");
								s = inputATM("(0 per uscire) Valore: ", 10, conto);
								if (s.equals("")) {
									continue;
								}
								if (s.equals("0")) {
									break;
								} else {
									utente.getConti().get(accountNumber).balance -= Double.parseDouble(s);
									my_bank.findOwnerGivenAccount(conto)
											.addTransaction(new Transaction(utente.getConti().get(accountNumber),
													utente.getConti().get(accountNumber), 0 - Double.parseDouble(s),
													my_bank));
									System.out.println("Hai prelevato " + s + "!");
									flag = true;
									break;
								}
							}
							if (flag) {
								break;
							}
						}
					}
					break; // exit case
				}
				case "2": {
					while (!s.equals("0")) {
						boolean flag = false;
						System.out.println(Constants.ANSI_GREEN + "\nSelezionare il conto: ");
						String opzValide[] = new String[utente.getConti().size() + 1];
						for (Account conto : utente.getConti()) {
							System.out.println(conto.toString());
							opzValide[conto.getId() - 1] = String.valueOf(conto.getId());
						}
						opzValide[opzValide.length - 1] = "0"; // Add zero to valid options
						System.out.println(Constants.ANSI_RESET + "---------------------------------");
						s = inputATM("(0 per uscire) Valore: ", opzValide);
						if (s.equals("")) {
							continue;
						}
						if (s.equals("0")) {
							break;
						} else {
							final int accountNumber = Integer.valueOf(s) - 1;
							Account conto = utente.getConti().get(accountNumber);
							while (!s.equals("0")) {
								System.out.println(Constants.ANSI_GREEN
										+ "\nInserisci l'importo da depositare (usa il punto per i decimali)"
										+ Constants.ANSI_RESET);
								System.out.println(Constants.ANSI_RESET + "---------------------------------");

								System.out.print("(0 per uscire) Valore: ");
								Scanner scan = new Scanner(System.in);
								String s_value = scan.nextLine();
								Double value = 0.0;
								try {
									value = Double.parseDouble(s_value);
								} catch (Exception e) {
									System.out.println("Attenzione, i valori inseriti non sono ammessi");
									continue;
								}

								if (value < 0 || value > 1000) {
									System.out.println("Attenzione, " + value + " non è un valore valido");
									System.out.println("L'intervallo ammesso è: [" + 10 + ", " + 1000 + "]");
									System.out.println();
									continue;
								}
								if (s.equals("0")) {
									break;
								} else { // Porto a termine l'operazione
									my_bank.getSingleConto(accountNumber).balance += value;
									my_bank.findOwnerGivenAccount(conto)
											.addTransaction(new Transaction(utente.getConti().get(accountNumber),
													utente.getConti().get(accountNumber), value, my_bank));
									System.out.println(Constants.ANSI_GREEN + "\nHai depositato " + value + "!"
											+ Constants.ANSI_RESET);
									flag = true; // flag usato per determinare se l'operazione è andata a buon fine
									break;
								}
							}
							if (flag) {
								break;
							}
						}
					}
					break; // exit case
				}
				case "3": {
					while (!s.equals("0")) {
						boolean flag = false; // flag usato per deteminare se l'operazione è andata a buon fine
						System.out.println(Constants.ANSI_PURPLE + "\nSelezionare il conto di origine: ");
						String opzValide[] = new String[utente.getConti().size() + 1];
						for (Account conto : utente.getConti()) {
							System.out.println(conto.toString());
							opzValide[conto.getId() - 1] = String.valueOf(conto.getId());
						}
						opzValide[opzValide.length - 1] = "0"; // Add zero to valid options
						System.out.println(Constants.ANSI_RESET + "---------------------------------");
						s = inputATM("(0 per uscire) Valore: ", opzValide);
						if (s.equals("")) {
							continue;
						}
						if (s.equals("0")) {
							break;
						} else {
							final int accountNumber = Integer.valueOf(s) - 1;
							Account conto = utente.getConti().get(accountNumber);
							while (!s.equals("0")) {
								System.out.println(Constants.ANSI_PURPLE
										+ "\nInserisci l'importo da inviare (usa il punto per i decimali)");
								System.out.println(Constants.ANSI_RESET + "---------------------------------");
								s = inputATM("(0 per uscire) Valore: ", 5, conto);
								Double importo = 0.0;
								try {
									importo = Double.parseDouble(s);
								} catch (Exception e) {
									System.out.println("Caratteri non validi");
									continue;
								}

								if (s.equals("")) {
									continue;
								}
								if (s.equals("0")) {
									break;
								} else {
									while (!s.equals("0")) {
										System.out
												.println(Constants.ANSI_PURPLE + "\nInserisci l'IBAN del destinatario");
										System.out.println(Constants.ANSI_RESET + "---------------------------------");
										String listaConti[] = new String[my_bank.getConti().size()];
										for (int i = 0; i < listaConti.length; i++) {
											listaConti[i] = my_bank.getConti().get(i).id + "";
										}
										s = inputATM("(0 per uscire) Valore: ", listaConti);
										if (s.equals("")) {
											continue;
										}
										if (s.equals("0")) {
											break;
										} else {
											// Eseguo il bonifico
											my_bank.getSingleConto(accountNumber).balance -= importo;
											my_bank.getSingleConto(Integer.parseInt(s) - 1).balance += importo;
											my_bank.findOwnerGivenAccount(conto).addTransaction(new Transaction(
													my_bank.getSingleConto(accountNumber),
													my_bank.getSingleConto(Integer.parseInt(s)-1), importo, my_bank));
											System.out.println("Bonifico da " + utente.getName() + " a "
													+ my_bank.getCustomers()
															.get(my_bank.getSingleConto(Integer.parseInt(s)-1).getId()-1).getName()
													+ " di importo " + importo + " effettuato!");
											flag = true;
											break;
										}
									}

								}
								break;
							}
							if (flag) {
								break;
							}
						}
					}
					break; // exit case
				}
				case "4": {
					System.out.print(Constants.ANSI_RED+"Vuoi aprire un conto deposito? [y/n]: " );
					Scanner scan = new Scanner(System.in);
					String answer = scan.nextLine();
					if(answer.toLowerCase().equals("y")) {
						DepositAccount temp = new DepositAccount(my_bank);
						my_bank.getSingleCustomer(0).addConto(temp);
						System.out.println("Il nuovo conto è stato aperto!");
						System.out.println("Il tasso di interesse è " + (Double) temp.accept(new ATM()) * 100 + "% lordo" + Constants.ANSI_RESET);
					}
					else {
						System.out.println(Constants.ANSI_RED+"Nessun conto deposito è stato aperto"+Constants.ANSI_RESET);
					}
					break;
				}
				case "5": {
					break;
				}
				case "0": {
					System.out.println("\nGrazie per aver usato il nostro ATM!");
					System.out.println("Vuoi fare un'altra operazione? (y/n)");
					s = in.nextLine();
					if (s.toLowerCase().equals("y")) {
						continue;
					}
					if (s.toLowerCase().equals("n")) {
						s = "0";
						System.out.println("Spegnimento...");
						Thread.sleep(1000);
						System.out.println("...");
						Thread.sleep(1000);
						System.out.println("...");
						Thread.sleep(1000);
						System.out.println("Arrivederci");
						break;
					}
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
