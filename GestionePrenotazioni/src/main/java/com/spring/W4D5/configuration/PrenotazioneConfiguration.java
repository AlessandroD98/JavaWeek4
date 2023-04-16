package com.spring.W4D5.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import com.spring.W4D5.model.Prenotazione;
import com.spring.W4D5.model.Utente;

@Configuration
public class PrenotazioneConfiguration {

	@Bean("CustomPrenotazione")
	@Scope("prototype")
	public Prenotazione customPrenotazione() {
		Prenotazione p = new Prenotazione();
		p.setDataPrenotazione();
		p.setDataFinePrenotazione(p.getDataprenotazione());
		return p;
		
	}
	
}
