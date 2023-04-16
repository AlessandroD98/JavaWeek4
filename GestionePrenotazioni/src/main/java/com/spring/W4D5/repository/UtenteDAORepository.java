package com.spring.W4D5.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.W4D5.model.Utente;

@Repository
public interface UtenteDAORepository extends CrudRepository<Utente, Long> {
	
	

}
