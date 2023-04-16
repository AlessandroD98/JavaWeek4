package com.spring.W4D5.model;


import java.util.List;

import com.spring.W4D5.Enum.Tipo_postazione;
import com.spring.W4D5.runner.RunnnerApp;
import com.spring.W4D5.service.EdificioService;
import com.spring.W4D5.service.PostazioneService;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "postazioni")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Postazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postazione_id;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private Tipo_postazione tipo;
	private Integer numeroMaxOccupanti;
	private Boolean stato;
	@ManyToOne
    @JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	public Postazione(String descrizione, Tipo_postazione tipo, Integer numeroMaxOccupanti, Boolean stato,
			Edificio edificio) {
		super();
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.numeroMaxOccupanti = numeroMaxOccupanti;
		this.stato = stato;
		this.edificio = edificio;
	}

	public static  void menuPostazione(PostazioneService postazioneService,EdificioService edificioService) {
		
		Boolean exit = false;
		while(!exit) {
			System.out.println("\n >>Seleziona una delle voci dal menu | 0 per uscire \n"
					+ "\n1 CREA UNA NUOVA POSTAZIONE"
					+ "\n2 VISUALIZZA TUTTE LE POSTAZIONI"
					+ "\n3 ELIMINA UNA POSTAZIONE"
					+ "\n4 MODIFICA UNA POSTAZIONE");
			String scelta = RunnnerApp.s.nextLine();
			try {
				Integer query = Integer.parseInt(scelta);
				switch(query) {
				case 0:
					exit = true;
					break;
				case 1:
					creaPostazione(postazioneService,edificioService);
					break;
				case 2:
					showAllPostazioni(postazioneService);
					break;
				case 3:
					deletePostazione(postazioneService);
					break;
				case 4:
					break;
					default: System.out.println("Inserisci un valore valido");
				}
			} catch (Exception e) {
				System.out.println("Il valore inserito non è un numero"
						+ "\n " + e);
			}
		}
	}
	
	
	// Crea una nuova postazione
	
	  public static  void creaPostazione(PostazioneService postazioneService,EdificioService edificioService) {
		  if(edificioService.findAllEdifici().size() != 0) { 
			  //edificioService.findAllEdifici().forEach(e -> System.out.println(e));
		  try { 
			  System.out.println(">> Inserisci l'ID dell'edificio dove vuoi creare la postazione");
			  String scelta = RunnnerApp.s.nextLine();
			  try {
				  Long query = Long.parseLong(scelta);
				  Edificio e = edificioService.findById(query);
				  System.out.println("Inserisci una descrizione per la postazione");
				  String descrizione = RunnnerApp.s.nextLine();
				  Tipo_postazione tipoPostazione = selectTipoPostazione ();
				  postazioneService.createPostazione(e,descrizione, tipoPostazione); 
			  } catch(Exception e) {
				  System.out.println("Il valore inserito non è un numero");
			  }
		  } catch (Exception e) {
	  System.out.println("Errore nel salvataggio della Postazione");
	  } 
		  }else {
	  System.out.println("Prima crea un edificio!"); 
		  } 
	  }
	  
	  //  Visualizza tutte le postazioni
	  
	  public static void showAllPostazioni (PostazioneService postazioneService) {
		  if(postazioneService.findAllPostazioni().size() != 0) {
			  System.out.println("Sono state trovate: " + postazioneService.findAllPostazioni().size() + (postazioneService.findAllPostazioni().size() == 1 ? " Postazione" : " Postazioni"));
			  postazioneService.findAllPostazioni().forEach(p -> System.out.println(p));
		  } else {
			  System.out.println("Non ci sono postazioni");
		  }
	  }
	  
	  // Elimina una postazione
	  
	  public static void deletePostazione(PostazioneService postazioneService) {
		  if(postazioneService.findAllPostazioni().size() != 0) {
			 // postazioneService.findAllPostazioni().forEach(p -> System.out.println(p));
			  System.out.println(">> Inserisci l'ID della postazione che desideri eliminare");
			  String scelta = RunnnerApp.s.nextLine();
			  try {
				  Long query = Long.parseLong(scelta);
				  Postazione p = postazioneService.findPostazioneById(query);
				  if(p != null) {
					  postazioneService.removePostazione(p);
				  } else {
					  System.out.println("Postazione non trovata!");
				  }
			  } catch(Exception e) {
				  System.out.println("Il valore inserito non è valido");
			  }
		  } else {
			  System.out.println("Non ci sono postazioni da eliminare! ");
		  }
	  }
	  
	  // Seleziona il tipo di postazione
	  
	  public static Tipo_postazione  selectTipoPostazione () {
		  System.out.println(">> Seleziona il tipo di postazione che vuoi creare "
		  		+ "\n 1 PRIVATO - 2 OPENSPACE - 3 SALA RIUNIONI");
		
		  while(true) {
			  String scelta = RunnnerApp.s.nextLine();
			  try {
				  Integer query =Integer.parseInt(scelta);
				  switch(query) {
				  case 1:
					    return Tipo_postazione.PRIVATO;
					
				  case 2:
					  return Tipo_postazione.OPENSPACE;
					 
				  case 3:
					  return Tipo_postazione.SALA_RIUNIONI;
					 
					  default: System.out.println("Il valore inserito non è valido");
				  }	  
			  } catch (Exception e) {
				  System.out.println("Il valore inserito non è valido");
			  }
		  }
		  
	  }
	  
	  @Override
	  public String toString() {
	      if (this.edificio == null) {
	          return "Postazione [postazione_id=" + this.postazione_id + ", descrizione=" + this.descrizione +
	              ", tipo=" + this.tipo + ", numeroMaxOccupanti=" + this.numeroMaxOccupanti + ", stato=" + this.stato +
	              "]";
	      }
	      return "Postazione [postazione_id=" + this.postazione_id + ", descrizione=" + this.descrizione +
	          ", tipo=" + this.tipo + ", numeroMaxOccupanti=" + this.numeroMaxOccupanti + ", stato=" + this.stato +
	          ", edificio=" + this.edificio.getNome() + "]";
	  }
}
