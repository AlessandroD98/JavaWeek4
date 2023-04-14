package com.spring.W4D5.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "edifici")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long edificio_id;
	private String nome;
	private String indirizzo;
	private String città;
	@OneToMany(mappedBy = "edificio")
	private List<Postazione> postazioni;
	
}
