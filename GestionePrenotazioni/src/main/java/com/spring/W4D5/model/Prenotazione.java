package com.spring.W4D5.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "prenotazioni")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prenotazione_id;
	private LocalDate dataprenotazione;
	private LocalDate datafineprenotazione;
	@OneToOne
	@JoinColumn(name = "postazione_id")
	private Postazione postazione;
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;
}
