package com.spring.W4D5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.W4D5.model.Edificio;

@Repository
public interface EdificioDAORepository extends CrudRepository<Edificio, Long>{

	@Query(value="SELECT e FROM Edificio e WHERE e.città = :city") public List<Edificio> findByCitta(String city);
	
}
