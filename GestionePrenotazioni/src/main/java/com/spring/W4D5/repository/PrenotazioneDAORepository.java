package com.spring.W4D5.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.W4D5.model.Prenotazione;

@Repository
public interface PrenotazioneDAORepository extends CrudRepository<Prenotazione, Long> {

	@Query(value="SELECT p FROM Prenotazione p WHERE p.utente.id = :id") public List<Prenotazione> findAllPrenotazioniById(Long id);
	
	@Query(value="SELECT p FROM Prenotazione p WHERE p.utente.id = :id AND p.dataprenotazione = :date") public List<Prenotazione> findAllPrenotazioniData(Long id,LocalDate date);
}
