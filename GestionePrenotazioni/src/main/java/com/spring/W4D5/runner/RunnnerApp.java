package com.spring.W4D5.runner;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.spring.W4D5.model.Edificio;
import com.spring.W4D5.model.Postazione;
import com.spring.W4D5.model.Prenotazione;
import com.spring.W4D5.model.Utente;
import com.spring.W4D5.service.EdificioService;
import com.spring.W4D5.service.PostazioneService;
import com.spring.W4D5.service.PrenotazioneService;
import com.spring.W4D5.service.UtenteService;

@Component
public class RunnnerApp implements ApplicationRunner {
	
	public static Scanner s = new Scanner(System.in);
	
	@Autowired EdificioService edificioService;
	@Autowired PostazioneService postazioneService;
	@Autowired PrenotazioneService prenotazioneService;
	@Autowired UtenteService utenteService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		gestionePrenotazioni();
	}
	
	public void gestionePrenotazioni() {
		while(true) {
			System.out.println("\n //---- Benvenuto nel menu di gestione delle prenotazioni ----// \n"
					+ "\n >>Seleziona una delle voci dal menu: \n"
					+ "\n1 GESTIONE PRENOTAZIONI"
					+ "\n2 GESTIONE UTENTI"
					+ "\n3 GESTIONE EDIFICI"
					+ "\n4 GESTIONE POSTAZIONI");
			String scelta = s.nextLine();
			try {
				Integer query = Integer.parseInt(scelta);
				switch(query) {
				case 1:
					Prenotazione.menuPrenotazioni(prenotazioneService,utenteService ,postazioneService);
					break;
				case 2:
					Utente.menuUtente(utenteService);
					break;
				case 3:
					Edificio.menuEdificio(edificioService);
					break;
				case 4:
					Postazione.menuPostazione(postazioneService,edificioService);
					break;
				default: System.err.println("Inserisci un valore valido");
				}
			} catch (Exception e) {
				System.out.println("Il valore inserito non è un numero");
			}
		}
		
	}
	
}
