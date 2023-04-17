package com.spring.W4D5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.W4D5.Enum.Tipo_postazione;
import com.spring.W4D5.model.Postazione;

@Repository
public interface PostazioneDAORepository extends CrudRepository<Postazione, Long> {

	@Query(value="SELECT p FROM Postazione p WHERE p.tipo = :tipo") public List<Postazione> findByTipo(Tipo_postazione tipo);
	
	@Query(value="SELECT p FROM Postazione p WHERE p.edificio.città = :city AND p.stato = false AND p.tipo = :type") public List<Postazione> findByCity(String city, Tipo_postazione type);
	
}
