package com.spring.W4D5.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.W4D5.runner.RunnnerApp;
import com.spring.W4D5.service.EdificioService;

import jakarta.persistence.CascadeType;
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
@Table(name = "edifici")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long edificio_id;
	private String nome;
	private String indirizzo;
	private String città;
	@OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Postazione> postazioni;
	
	public Edificio(String nome, String indirizzo, String città, List<Postazione> postazioni) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.città = città;
		this.postazioni = postazioni;
	}
	
	public static void menuEdificio(EdificioService edificioservice) {
		Boolean exit = false;
		while(!exit) {
			System.out.println("\n >>Seleziona una delle voci dal menu | 0 per uscire \n"
					+ "\n1 CREA UN NUOVO EDIFICIO"
					+ "\n2 VISUALIZZA TUTTI GLI EDIFICi"
					+ "\n3 ELIMINA UN EDIFICIO"
					+ "\n4 MODIFICA UN EDIFICIO");
			String scelta = RunnnerApp.s.nextLine();
			try {
				Integer query = Integer.parseInt(scelta);
				switch(query) {
				case 0:
					exit = true;
					break;
				case 1:
					creaEdificio(edificioservice);
					break;
				case 2:
					showAllEdifici(edificioservice);
					break;
				case 3:
					eliminaEdificio(edificioservice);
					break;
				case 4:
					modificaEdificio(edificioservice);
					break;
					default: System.out.println("Inserisci un valore valido");
				}
			} catch (Exception e) {
				System.out.println("Il valore inserito non è un numero " + e);
			}
		}
	}
	
	// Crea un edificio
	
	public static void creaEdificio(EdificioService edificioservice) {
		try {
			edificioservice.createEdificio();
			System.out.println("Edificio salvato nel DB!");
		} catch(Exception e) {
			System.out.println("Errore nel salvataggio dell'Edificio " + e);
		}
	}
	
	// Visualizza tutti gli edifici
	
	public static void showAllEdifici(EdificioService edificioservice) {
		List<Edificio> lista = edificioservice.findAllEdifici();
		if(lista.size() != 0) {
			lista.forEach(e -> System.out.println(e));
		} else {
			System.out.println("Non ci sono edifici!");
			}	
		}
	
	// Rimuove un edificio
	
	public static void eliminaEdificio(EdificioService edificioservice) {
		if(edificioservice.findAllEdifici().size() != 0) {
			System.out.println(">> Inserisci l'ID dell'Edificio che desideri eliminare");
			String scelta = RunnnerApp.s.nextLine();
			try {
				Long query = Long.parseLong(scelta);
				Edificio e = edificioservice.findById(query);
				if(e !=  null) {
					edificioservice.removeEdificio(e);
				} else {
					System.out.println("Edificio non trovato!");
				}
			} catch(Exception e) {
				System.out.println("Il valore inserito nonn à valido");
			}
		} else {
			System.out.println("Non ci sono Edifici da eliminare!");
		}
	}
	
	// Modifica un edificio
	
	public static void modificaEdificio(EdificioService edificioservice) {
		
	}
}
