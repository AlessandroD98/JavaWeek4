package com.spring.W4D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.W4D5.model.Postazione;
import com.spring.W4D5.model.Prenotazione;
import com.spring.W4D5.model.Utente;
import com.spring.W4D5.repository.PrenotazioneDAORepository;

@Service
public class PrenotazioneService {

	@Autowired private PrenotazioneDAORepository repo;
	
	@Autowired @Qualifier("CustomPrenotazione") private ObjectProvider<Prenotazione> customPrenotazioneProvider;
	
	public void createCustomPrenotazione(Utente u, Postazione pos) {
		Prenotazione p = customPrenotazioneProvider.getObject();
		p.setUtente(u);
		p.setPostazione(pos);
		insertPrenotazione(p);
		System.out.println("Prenotazione creata correttamente");
	}
	
	public void insertPrenotazione(Prenotazione p) {
		repo.save(p);
	}
	
	public List<Prenotazione> findAllPrenotazioni() {
		return (List<Prenotazione>) repo.findAll();
	}
	
	public List<Prenotazione> findPrenotazioneByIdutente(Long id) {
		return repo.findAllPrenotazioniById(id);
	}
}
