import java.util.ArrayList;

public class GuestsList {

	private int nrLocuriLibere;
	private ArrayList<Guest> listaParticipanti;
	private ArrayList<Guest> listaAsteptare;
	
	public GuestsList(int nrLocuriLibere) {
		this.nrLocuriLibere = nrLocuriLibere;
		this.listaParticipanti = new ArrayList<Guest>();
		this.listaAsteptare = new ArrayList<Guest>();
	}
	
	public boolean isUsed(ArrayList<Guest> list, Guest gst) {
		for(int i = 0; i < list.size(); i++) {
			Guest participant = list.get(i);
			if (participant.isUsed(gst)) {
				return true;
			}
		}
		return false;
	}
	
//	1.adaugarea unei noi persoane (inscrise)
	public int addGuest (Guest gst) {
		if (this.isUsed(this.listaParticipanti, gst) || (this.isUsed(listaAsteptare, gst))) {
			System.out.println(gst.getName() + " este deja inscris la eveniment");
			return -1;
		}
		if (this.getLocuriLibere() > 0) {
			this.listaParticipanti.add(gst);
			System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
			return 0;
		} else {
			this.listaAsteptare.add(gst);
			int x = this.listaAsteptare.indexOf(gst) + 1;
			System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " 
								+ x + ". Te vom notifica daca un loc devine disponibil.");
			return x;
		}
		
	}
	
//	2. determina daca o persoana este inscrisa la eveniment (in oricare lista).
	public boolean verifInscris(Guest gst) {
		if (gst != null) {
//			System.out.println("Persoana este inscrisa la eveniment");
			return true;
		}
		System.out.println("Persoana nu este inscrisa la eveniment");
		return false;
	}
	
// 	3. eliminarea unei persoane (inscrise)
	public boolean remove (Guest gst) {
		if (verifInscris(gst)) {
			this.listaParticipanti.remove(gst);
			System.out.println("Persoana a fost stearsa");
			if (!this.listaAsteptare.isEmpty()) {
				Guest mutaPers = this.listaAsteptare.get(0);
				this.listaAsteptare.remove(mutaPers);
				this.listaParticipanti.add(mutaPers);
				System.out.println(mutaPers.getName() + " a fost mutata in lista participantilor");
			}
			return true;
		}
		System.out.println("Eroare! Persoana nu era inscrisa!");
		return false;
	}
	
//	4. actualizarea detaliilor unei persoane inscrise
	public void update(Guest gst, String[] str1) {
		if (gst == null) {
			System.out.println("Persoana nu exista");
		}
		switch (str1[2]) {
			case "name":
				gst.setLastName(str1[0]);
				gst.setFirstName(str1[1]);
				System.out.println("Persoana a fost updatata");
				break;
			case "email" :
				gst.setEmail(str1[0]);
				System.out.println("Persoana a fost updatata");
				break;
			case "phoneNumber" :
				gst.setPhoneNumber(str1[0]);
				System.out.println("Persoana a fost updatata");
				break;
			default:
				System.out.println("Comanda introdusa este invalida");
				break;
		}
	}
	
	
//	5. obtinerea listei de persoane care au loc la eveniment (i.e. lista de participare)
	public void printListaParticipanti() {
		if (this.getNrParticipanti() <= 0) {
			System.out.println("Nicio persoana nu a fost inscrisa");
		} else {
			for (int i = 0; i < this.getNrParticipanti(); i++) {
				System.out.println(i + 1 + ". Nume: " + this.listaParticipanti.get(i).getName() 
								+ "\tEmail: " + this.listaParticipanti.get(i).getEmail() + "\tTel: " 
								+ this.listaParticipanti.get(i).getPhoneNumber());
			}
		}
	}
	
//	6. obtinerea listei de persoane din lista de asteptare
	public void printListaAsteptare() {
		if (this.getNrListaAsteptare() <= 0) {
			System.out.println("Lista de asteptare este goala");
		} else {
			for (int i = 0; i < this.getNrListaAsteptare(); i++) {
				System.out.println(i + 1 + ". Nume: " + this.listaAsteptare.get(i).getName() 
						+ "\tEmail: " + this.listaAsteptare.get(i).getEmail() + "\tTel: " 
						+ this.listaAsteptare.get(i).getPhoneNumber());
			}
		}
	}
		
//	7. obtinerea numarului de locuri disponibile
	public int getLocuriLibere() {
		return this.nrLocuriLibere - getNrParticipanti();
	}
	
//	8. obtinerea numarului de persoane participante (i.e. aflate in lista de participare)
	public int getNrParticipanti() {
		return this.listaParticipanti.size();
	}
	
//	9. obtinerea numarului de persoane din lista de asteptare
	public int getNrListaAsteptare() {
		return this.listaAsteptare.size();
	}
	
//	10. obtinerea numarului total de persoane
	public int nrTotalPersoane() {
		return getNrParticipanti() + getNrListaAsteptare();
	}
 	
// 11. cautare partiala, dupa un subsir de caractere:
	public ArrayList<Guest> partialSearch (String searchStr) {
		searchStr = searchStr.toLowerCase();
		ArrayList<Guest> searchList = new ArrayList<Guest>();
		for (int i = 0; i < this.getNrParticipanti(); i++) {
			Guest person = this.listaParticipanti.get(i);
			if (person.getName().toLowerCase().contains(searchStr) 
				|| person.getEmail().toLowerCase().contains(searchStr)
				|| person.getPhoneNumber().toLowerCase().contains(searchStr)) {
				searchList.add(person);
			}
		}
		for (int j = 0; j < this.getNrListaAsteptare(); j++) {
			Guest person2 = this.listaAsteptare.get(j);
			if (person2.getName().toLowerCase().contains(searchStr) 
					|| person2.getEmail().toLowerCase().contains(searchStr)
					|| person2.getPhoneNumber().toLowerCase().contains(searchStr)) {
					searchList.add(person2);
				}
		}
		return searchList;
	}
	
	public Guest checkGuestByName(String fullName) {
		for (int i = 0; i < this.getNrParticipanti(); i++) {
			Guest registredParticipant = this.listaParticipanti.get(i);
			if (registredParticipant.getName().equalsIgnoreCase(fullName)) {
				return registredParticipant;
			}
		}
		for (int i = 0; i < this.getNrListaAsteptare(); i++) {
			Guest queuedParticipant = this.listaAsteptare.get(i);
			if (queuedParticipant.getName().equalsIgnoreCase(fullName)) {
				return queuedParticipant;
			}
		}
		System.out.println("Persoana " + fullName + " nu este inscrisa la eveniment");
		return null;
	}
		
	public Guest checkGuestByEmail(String email) {
		for (int i = 0; i < this.getNrParticipanti(); i++) {
			Guest registredParticipant = this.listaParticipanti.get(i);
			if (registredParticipant.getEmail().equalsIgnoreCase(email)) {
				return registredParticipant;
			}
		}
		for (int i = 0; i < this.getNrListaAsteptare(); i++) {
			Guest queuedParticipant = this.listaAsteptare.get(i);
			if (queuedParticipant.getEmail().equalsIgnoreCase(email)) {
				return queuedParticipant;
			}
		} 
		System.out.println("Persoana cu emailul " + email + " nu este inscrisa la eveniment");
		return null;
	}
		
	public Guest checkGuestByPhoneNo(String phoneNo) {
		for (int i = 0; i < this.getNrParticipanti(); i++) {
			Guest registredParticipant = this.listaParticipanti.get(i);
			if (registredParticipant.getPhoneNumber().equalsIgnoreCase(phoneNo)) {	
				return registredParticipant;
			}
		}
		for (int i = 0; i < this.getNrListaAsteptare(); i++) {
			Guest queuedParticipant = this.listaAsteptare.get(i);
			if (queuedParticipant.getPhoneNumber().equalsIgnoreCase(phoneNo)) {
				return queuedParticipant;
			}
		} 
		System.out.println("Persoana cu numarul de telefon " + phoneNo + " nu este inscrisa la eveniment");
		return null;
	}
}
