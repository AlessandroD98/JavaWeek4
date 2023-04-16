package com.spring.W4D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.W4D5.Enum.Tipo_postazione;
import com.spring.W4D5.model.Edificio;
import com.spring.W4D5.model.Postazione;
import com.spring.W4D5.repository.PostazioneDAORepository;

@Service
public class PostazioneService {

	@Autowired private PostazioneDAORepository repo;
	
	@Autowired @Qualifier("FakePostazione") private ObjectProvider<Postazione> fakePostazioneProvider;
	
	public void createPostazione(Edificio e, String s, Tipo_postazione tipo) {
		Postazione p = fakePostazioneProvider.getObject();
		p.setEdificio(e);
		p.setDescrizione(s);
		p.setTipo(tipo);
		insertPostazione(p);
		System.out.println("Postazione creata con successo!");
	}

	public void insertPostazione(Postazione p) {
		repo.save(p);
	}
	
	public List<Postazione> findByStatus(Tipo_postazione privato) {
		return repo.findByTipo(privato);
	}
	
	public List<Postazione> finByCity(String city){
		return repo.findByCity(city);
	}
	
	public Postazione findPostazioneById(Long id) {
		return repo.findById(id).get();
	}
	
	public List<Postazione> findAllPostazioni() {
		return (List<Postazione>) repo.findAll();
	}
	
	public void removePostazione(Postazione p) {
		repo.delete(p);
		System.out.println("Postazione eliminata dal DB!");
	}
	
	public void UpdatePostazione(Postazione p) {
		repo.save(p);
	}
}
