import java.util.Scanner;

public class Main {

	public static void help() {
		System.out.println("help			- Afiseaza aceasta lista de comenzi");
		System.out.println("add				- Adauga o noua persoana (inscriere)");
		System.out.println("check			- Verifica daca o persoana este inscrisa la eveniment");
		System.out.println("remove			- Sterge o persoana existenta din lista");
		System.out.println("update			- Actualizeaza detaliile unei persoane");
		System.out.println("guests			- Lista de persoane care participa la eveniment");
		System.out.println("waitlist		- Persoanele din lista de asteptare");
		System.out.println("available		- Numarul de locuri libere");
		System.out.println("guests_no		- Numarul de persoane care participa la eveniment");
		System.out.println("waitlist_no		- Numarul de persoane din lista de asteptare");
		System.out.println("subscribe_no	- Numarul total de persoane inscrise");
		System.out.println("search			- Cauta toti invitatii conform sirului de caractere introdus");
		System.out.println("quit			- Inchide aplicatia");
	}

	public static Guest createGuest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti numele de familie: ");
		String nume = sc.nextLine();
		System.out.println("Introduceti prenumele: ");
		String prenume = sc.nextLine();
		System.out.println("Introduceti email: ");
		String email = sc.nextLine();
		System.out.println("Introduceti numarul de telefon (format \"+0733386463\") ");
		String numarTelefon = sc.nextLine();
		Guest newGuest = new Guest(nume, prenume, email, numarTelefon);
		return newGuest;
		
	}
	
	public static Guest checkGuest(GuestsList list) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Alege modul de autentificare, tastand:");
		System.out.println("\t\"1\"- Nume si Prenume \n\t\"2\"- Email \n\t\"3\"- Nr. Tel");
		int alege = sc.nextInt();
		sc.nextLine();
		switch (alege) {
		case 1:
			System.out.println("Introduceti numele de familie: ");
			String lastName = sc.nextLine();
			System.out.println("Introduceti prenumele: ");
			String firstName = sc.nextLine();
			String fullName = lastName + " " + firstName;
			return list.checkGuestByName(fullName);
		case 2:
			System.out.println("Introduceti adresa de email: ");
			String email = sc.nextLine();
			return list.checkGuestByEmail(email);
		case 3:
			System.out.println("Introduceti numarul de telefon (format \"+0733386463\")");
			String phoneNo = sc.nextLine();
			return list.checkGuestByPhoneNo(phoneNo);
		default:
			System.out.println("Comanda introdusa nu este valida. \nIncercati inca o data.");
			break;
	}
	return null;
	}
	
	private static String[] updateCommand() {
		Scanner sc = new Scanner(System.in);
		String[] str = new String[3];
		System.out.println("Alege ce vrei sa modifici: ");
		System.out.println("\t 1. Nume si Prenume \n\t 2. Email \n\t 3. Numar de telefon (format \"+0733386463\")");
		int comanda = sc.nextInt();
		sc.nextLine();
		switch (comanda) {
			case 1:
				System.out.println("Te rog sa introduci numele de familie nou: ");
				String newLastName = sc.nextLine();
				str[0] = newLastName;
				System.out.println("Te rog sa introduci prenumele nou: ");
				String newFirstName = sc.nextLine();
				str[1] = newFirstName;
				str[2] = "name";
				break;
			case 2:
				System.out.println("Te rog sa introduci noul email: ");
				String newEmail = sc.nextLine();
				str[0] = newEmail;
				str[2] = "email";
				break;
			case 3:
				System.out.println("Te rog sa introduci noul numar de telefon: ");
				String newPhoneNo = sc.nextLine();
				str[0] = newPhoneNo;
				str[2] = "phoneNo";
				break;
			default:
				System.out.println("Comanda introdusa nu este valida. \nIncercati inca o data.");
				break;
		}
		return str;
	}
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bun venit! Introduceti numarul de locuri disponibile: ");
		while (!sc.hasNextInt()) {
			   System.out.println("Introduceti un numar!");
			   sc.nextLine();
		}
		int locuri = sc.nextInt();
		GuestsList guestList= new GuestsList(locuri);
		boolean turnOff = true;
		sc.nextLine();
		while (turnOff) {
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			String comanda = sc.nextLine();
			switch(comanda) {
				case "help":
					help();
					break;
				case "add":
					System.out.println("Se adauga o noua persoana…");
					guestList.addGuest(createGuest());
					break;
				case "check":
					guestList.verifInscris(checkGuest(guestList));
					break;
				case "remove":
					System.out.println("Se sterge o persoana existenta din lista…");
					guestList.remove(checkGuest(guestList));
					break;
				case "update":
					Guest guestForUpdate = checkGuest(guestList);
					if (guestForUpdate != null) {
						guestList.update(guestForUpdate, updateCommand());
					}
					break;
				case "guests":
					guestList.printListaParticipanti();
					break;
				case "waitlist":
					guestList.printListaAsteptare();
					break;
				case "available" :
					System.out.println("Numarul de locuri ramase: " + guestList.getLocuriLibere());
					break;
				case "guests_no":
					System.out.println("Numarul de participanti: " + guestList.getNrParticipanti());
					break;
				case "waitlist_no":
					System.out.println("Dimensiunea listei de asteptare asteptare: " + guestList.getNrListaAsteptare());
					break;
				case "subscribe_no":
					System.out.println("Numarul total de persoane: " + guestList.nrTotalPersoane());
					break;
				case "search":
					System.out.println("Introduceti sirul de caractere dupa care vreti sa faceti cautarea: ");
					String search = sc.nextLine();
					guestList.partialSearch(search);
					break;
				case "quit":
					turnOff = false;
					System.out.println("Aplicatia se inchide...");
					break;
				default:
					System.out.println("Comanda introdusa nu este valida. \nIncercati inca o data.");
					break;
				}
			}
	}

}
