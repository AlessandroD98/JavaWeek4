package com.spring.W4D5.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.W4D5.model.Edificio;
import com.spring.W4D5.repository.EdificioDAORepository;

@Service
public class EdificioService {

	@Autowired private EdificioDAORepository repo;
	
	@Autowired @Qualifier("FakeEdificio") private ObjectProvider<Edificio> fakeEdificioProvider;
	
	public void createEdificio() {
		insertEdificio(fakeEdificioProvider.getObject());
	}
	
	public void insertEdificio(Edificio e) {
		repo.save(e);
	}
	
	public List<Edificio> findCittaIn(String city) {
		return repo.findByCitta(city);
	}
	
	public List<Edificio> findAllEdifici() {
		return (List<Edificio>) repo.findAll();
	}
	
	public Edificio findById(Long id) {
		return repo.findById(id).get();
	}
	
	public void removeEdificio(Edificio e) {
		repo.delete(e);
		System.out.println("Edificio rimosso dal DB!");
	}
}
