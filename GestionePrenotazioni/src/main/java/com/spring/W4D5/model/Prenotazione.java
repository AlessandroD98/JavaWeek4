package com.spring.W4D5.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.spring.W4D5.Enum.Tipo_postazione;
import com.spring.W4D5.runner.RunnnerApp;
import com.spring.W4D5.service.PostazioneService;
import com.spring.W4D5.service.PrenotazioneService;
import com.spring.W4D5.service.UtenteService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "prenotazioni")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prenotazione_id;
	private LocalDate dataprenotazione;
	private LocalDate datafineprenotazione;
	@OneToOne
	@JoinColumn(name = "postazione_id")
	private Postazione postazione;
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;
	
	
	public Prenotazione(LocalDate dataprenotazione, LocalDate datafineprenotazione, Postazione postazione,
			Utente utente) {
		super();
		this.dataprenotazione = dataprenotazione;
		this.datafineprenotazione = datafineprenotazione;
		this.postazione = postazione;
		this.utente = utente;
	}
	
	public void setDataFinePrenotazione(LocalDate date) {
		LocalDate newDate = date.plusDays(1);
		this.datafineprenotazione = newDate;
	}
	
	public void setDataPrenotazione(LocalDate date) {
		this.dataprenotazione = date;
		/*
		 * System.out.println(" >>Inserisci la data della prenotazione (dd-mm-yyyy)");
		 * String s = RunnnerApp.s.nextLine(); while(true) { try { DateTimeFormatter
		 * formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); LocalDate data =
		 * LocalDate.parse(s, formatter); this.dataprenotazione = data; break; } catch
		 * (Exception e) { System.out.println("Inserisci una data valida"); } }
		 */
	}
	
	
	//static UtenteService utenteService;
	
	
	//static PostazioneService postazioneService;
	
	public static void menuPrenotazioni(PrenotazioneService prenotazioneService,UtenteService utenteService,PostazioneService postazioneService) {
		Boolean exit = false;
		while(!exit) {
			System.out.println("\n >>Seleziona una delle voci dal menu | 0 per uscire \n"
					+ "\n1 CREA UN NUOVA PRENOTAZIONE"
					+ "\n2 VISUALIZZA LE PRENOTAZIONI DI UN UTENTE"
					+ "\n3 ELIMINA UNA PRENOTAZIONE"
					+ "\n4 MODIFICA UNA PRENOTAZIONE");
			String scelta = RunnnerApp.s.nextLine();
			try {
				Integer query = Integer.parseInt(scelta);
				switch(query) {
				case 0:
					exit = true;
					break;
				case 1:
					creaPrenotazione(prenotazioneService,utenteService,postazioneService);
					break;
				case 2:
					showPrenotazioniUtente(prenotazioneService);
					break;
				case 3:
					break;
				case 4:
					break;
				default: System.out.println("Inserisci un valore valido");
				}
			} catch (Exception e) {
				System.out.println("Il valore inserito non è un numero" + e);
			}
		}
	}
	


	public static void creaPrenotazione(PrenotazioneService prenotazioneService,  UtenteService utenteService, PostazioneService postazioneService) {
		if(utenteService.findAllUtenti().size() == 0 || utenteService.findAllUtenti() == null) {
			System.out.println("Per creare un prenotazione è necessario un ID Utente! Creane prima uno");
		} else {
		Utente ut = ricercaUtente(utenteService);
		if(ut != null) {
			String nomeCItta = ricercaCitta();
			Tipo_postazione type = selctTypePostazione();
			List<Postazione> lista = postazioneService.finByCity(nomeCItta, type);
			if(lista.size() != 0) {
				lista.forEach(l -> System.out.println(l));
				Postazione p = ricercaPostazione(postazioneService);
				LocalDate data = checkDate();
				if(prenotazioneService.findPrenotazioniSameData(ut.getUtente_id(), data).size() == 0) {
					if(!p.getStato()) {
						p.setStato(true);
						postazioneService.insertPostazione(p);
						prenotazioneService.createCustomPrenotazione(ut,p,data);
					} else {
						System.out.println("La postazione risulta già occupata");
					}
			} else {
				System.out.println("Hai già delle prenotazioni nello stesso giorno!");
			}
		} else {
			System.out.println("Non ci sono postazioni libere che rispettano i criteri di ricerca");
		}
		} else {
			System.out.println("Utente non trovato");
			}		
		}
	}
	
	public static Utente ricercaUtente(UtenteService utenteService) {
		System.out.println(">> Inserisci l'ID Utente con il quale vuoi effettuare la prenotazione");
		String scelta = RunnnerApp.s.nextLine();
		Utente u = null;
		try {
			Long query = Long.parseLong(scelta);
			u = utenteService.findUtenteByID(query);
		} catch (Exception e) {
			System.out.println("Il valore inserito non è un numero!");
		}
		return u;
	}
	
	public static String ricercaCitta() {
		System.out.println(">> Inserisci la città per visualizzare le postazioni libere");
		String nomeCitta = RunnnerApp.s.nextLine().toUpperCase();
		return nomeCitta;
	}
	
	
	/*
	 * public static List<Postazione> ricercaCitta(PostazioneService
	 * postazioneService) { System.out.
	 * println(">> Inserisci la città per visualizzare le postazioni libere");
	 * String nomeCitta = RunnnerApp.s.nextLine().toUpperCase(); List<Postazione>
	 * postazioni = postazioneService.finByCity(nomeCitta); return postazioni; }
	 */
	
	
	public static Tipo_postazione selctTypePostazione() {
	    System.out.println(">> Seleziona la tipologia di postazione che stai cercando: "
	            + "\n 1 PRIVATE - 2 OPENSAPCE - 3 SALA RIUNIONI");
	    String option = RunnnerApp.s.nextLine();
	    Tipo_postazione selectedType = null;
	    try {
	        Integer num = Integer.parseInt(option);
	        switch(num) {
	            case 1:
	                selectedType = Tipo_postazione.PRIVATO;
	                break;
	            case 2:
	                selectedType = Tipo_postazione.OPENSPACE;
	                break;
	            case 3:
	                selectedType = Tipo_postazione.SALA_RIUNIONI;
	                break;
	        }
	    } catch (Exception e) {
	        System.out.println("Il valore inserito non è un numero!");
	    }
	    return selectedType;
	}
	
	/*
	 * public static List<Postazione> selctTypePostazione(PostazioneService
	 * postazioneService) { System.out.
	 * println(">> Selziona la tipologia di postazione che stai cercando: " +
	 * "\n 1 PRIVATE - 2 OPENSAPCE - 3 SALA RIUNIONI"); String option =
	 * RunnnerApp.s.nextLine(); List<Postazione> list = null; try { Integer num =
	 * Integer.parseInt(option); switch(num) { case 1: list
	 * =postazioneService.findByStatus(Tipo_postazione.PRIVATO); break; case 2: list
	 * =postazioneService.findByStatus(Tipo_postazione.OPENSPACE); break; case 3:
	 * list = postazioneService.findByStatus(Tipo_postazione.SALA_RIUNIONI); break;
	 * } } catch (Exception e) {
	 * System.out.println("Il valore inserito non è un numero!"); } return list; }
	 */
	
	public static Postazione ricercaPostazione(PostazioneService postazioneService) {
		System.out.println(">> Inserisci l'ID della Postazione per confermare la prenotazione");
		String scelta = RunnnerApp.s.nextLine();
		Postazione p = null;
		try {
			Long query = Long.parseLong(scelta);
			p = postazioneService.findPostazioneById(query);
		} catch (Exception e) {
			System.out.println("Il valore inserito non è un numero!");
		}
		return p;
	}
	
	public static void showPrenotazioniUtente(PrenotazioneService prenotazioneService) {
		if(prenotazioneService.findAllPrenotazioni().size() != 0) {
			System.out.println(">> Inserici l'ID dell'Utente del quale vuoi trovare le prenotazioni");
			String scelta = RunnnerApp.s.nextLine();
			try {
				Long query = Long.parseLong(scelta);
				prenotazioneService.findPrenotazioneByIdutente(query).forEach(p -> System.out.println(p));
			} catch (Exception e) {
				System.out.println("Il valore inserito non ò valido!");
			}
		} else {
			System.out.println("Non ci sono ancora prenotazioni!");
		}
	}
	
	
public static LocalDate checkDate() {
	System.out.println(" >>Inserisci la data della prenotazione (dd-mm-yyyy)");
	String s = RunnnerApp.s.nextLine();
	LocalDate okDate;
	while(true) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate data = LocalDate.parse(s, formatter);
			okDate = data;
			break;
		} catch (Exception e) {
			System.out.println("Inserisci una data valida. Inserisci una data nel formato dd-mm-yyyy!");
		}
	}
	return okDate;
}
	
	@Override
	public String toString() {
	    return "Prenotazione{" +
	            "prenotazione_id=" + prenotazione_id +
	            ", dataprenotazione=" + dataprenotazione +
	            ", datafineprenotazione=" + datafineprenotazione +
	            ", postazione=" + postazione +
	            ", utente=" + (utente != null ? utente.getUtente_id() : null) +
	            '}';
	}
 }
