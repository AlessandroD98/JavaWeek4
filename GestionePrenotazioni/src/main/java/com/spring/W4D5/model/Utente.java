package com.spring.W4D5.model;

import java.util.List;

import com.spring.W4D5.runner.RunnnerApp;
import com.spring.W4D5.service.UtenteService;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long utente_id;
	private String username;
	private String name;
	private String lastname;
	private String email;
	@OneToMany(mappedBy = "utente" , fetch = FetchType.EAGER)
	private List<Prenotazione> prenotazioni;
	
	public Utente(String username, String name, String lastname, String email, List<Prenotazione> prenotazioni) {
		super();
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.prenotazioni = prenotazioni;
	}
	
	public static void menuUtente(UtenteService utenteService) {
		Boolean exit = false;
		while(!exit) {
			System.out.println("\n >>Seleziona una delle voci dal menu | 0 per uscire \n"
					+ "\n1 CREA UN NUOVO UTENTE"
					+ "\n2 VISUALIZZA TUTTI GLI UTENTI"
					+ "\n3 ELIMINA UN UTENTE"
					+ "\n4 MODIFICA UN UTENTE");
			String scelta = RunnnerApp.s.nextLine();
			try {
				Integer query = Integer.parseInt(scelta);
				switch(query) {
				case 0:
					exit = true;
					break;
				case 1:
					creaUtente(utenteService);
					break;
				case 2:
					showAllutenti(utenteService);
					break;
				case 3:
					 eliminaUtente(utenteService);
					break;
				case 4:
					break;
				default: System.out.println("Inserisci un valore valido");
				}
			} catch (Exception e) {
				System.out.println("Il valore inserito non è un numero");
			}
		}
	}
	
	// Crea un utente
	
	public static void creaUtente(UtenteService utenteService) {
		try {
			utenteService.createUtente();
		} catch (Exception e) {
			System.out.println("Errore durante la creazione dell'Utente");
		}
	}
	
	// Visualizza tutti gli utenti

	public static void showAllutenti(UtenteService utenteService) {
		try {
			List<Utente> lista = utenteService.findAllUtenti();
			lista.forEach(u -> System.out.println(u));
		} catch (Exception e) {
			System.out.println("Errore nel recupero degli Utenti");
		}
	}
	
	// Elimina un utente
	
	public static void eliminaUtente(UtenteService utenteService) {
		if(utenteService.findAllUtenti().size() != 0) {
			 System.out.println(">> Inserisci l'ID dell' Utente che desideri eliminare");
			  String scelta = RunnnerApp.s.nextLine();
			  try {
				  Long query = Long.parseLong(scelta);
				  Utente u = utenteService.findUtenteByID(query);
				  if(u != null) {
					  utenteService.removeUtente(u);;
				  } else {
					  System.out.println("Postazione non trovata!");
				  }
			  } catch(Exception e) {
				  System.out.println("Il valore inserito non è valido");
			  }
		} else {
			System.out.println("Non ci sono utenti da eliminare");
			}
	}
}
