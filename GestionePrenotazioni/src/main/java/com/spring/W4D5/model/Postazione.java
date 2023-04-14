package com.spring.W4D5.model;

import com.spring.W4D5.Enum.Tipo_postazione;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "postazioni")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Postazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postazione_id;
	private String descrizione;
	@Enumerated(EnumType.STRING)
	private Tipo_postazione tipo;
	private Integer numeroMaxOccupanti;
	private Boolean stato;
	@ManyToOne
    @JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	
	
}
