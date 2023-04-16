package com.spring.W4D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.W4D5.model.Utente;
import com.spring.W4D5.repository.UtenteDAORepository;

@Service
public class UtenteService {

	@Autowired private UtenteDAORepository repo;
	
	@Autowired @Qualifier("FakeUtente") private ObjectProvider<Utente> fakeUtenteProvider;
	
	public void createUtente() {
		insertUtente(fakeUtenteProvider.getObject());
		System.out.println("Utente creato correttamente!");
	}
	
	public void insertUtente(Utente u) {
		repo.save(u);
	}
	
	public List<Utente> findAllUtenti () {
		return (List<Utente>) repo.findAll();
	}
	
	public Utente findUtenteByID(Long id) {
		return repo.findById(id).get();	
		}
	
	public void removeUtente(Utente u) {
		repo.delete(u);
	}
}
