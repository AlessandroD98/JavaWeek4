package Main.tavolo;


import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Articolo.Drinks;
import Articolo.Franchise;
import Articolo.Pizza;
import Configuration.Configuration;
import Main.W4D1Application;
import Main.Ordine.Ordine;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tavoli")
public class Tavolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroTavolo;
	private int numeroMaxCoperti;
	private Boolean stato;
	@OneToOne
	@JoinColumn(name = "numeroOrdine")
	private Ordine ordine;
	
	
	public int getNumeroMaxCoperti() {
		return numeroMaxCoperti;
	}
	public void setNumeroMaxCoperti() {
		
		Random num = new Random();
		int max=10 , min= 1;
		this.numeroMaxCoperti = num.nextInt(max - min + 1) + min; 
		
	}
	public Boolean getStato() {
		return stato;
	}
	public void setStato(Boolean stato) {
		this.stato = stato;
	}
	public Long getNumeroTavolo() {
		return numeroTavolo;
	}
	
	public Ordine getOrdine() {
		return ordine;
	}
	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}
	
	public static Tavolo generaTavolo() {
		
AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Configuration.class);
		
Tavolo t = (Tavolo) appContext.getBean("tavolo");
		
		appContext.close();
		return t;
		
	}
	
	public static void generaTavoli() {
	
		Tavolo t =generaTavolo();
		 setTavoli(t);
		Tavolo t1 = generaTavolo();
		setTavoli(t1);
		Tavolo t2 = generaTavolo();
		setTavoli(t2);
		Tavolo t3 = generaTavolo();
		setTavoli(t3);
		Tavolo t4 = generaTavolo();
		setTavoli(t4);
	}
	
	public static void setTavoli(Tavolo t) {
		t.setStato(false);
		t.setNumeroMaxCoperti();
		TavoloDAO.saveTavolo(t);
	}

	public static void gestionetavoli() {
		System.out.println("Tavoli Liberi");
		List<Tavolo> arr1 = TavoloDAO.tavoliLiberi();
		System.out.println("Numero tavoli liberi : " + arr1.size());
		arr1.forEach(t -> System.out.println("Tavolo n : " + t.getNumeroTavolo() + " Numero posti : " + t.getNumeroMaxCoperti() + " \n"));
		System.out.println("Tavoli Occupati");
		List<Tavolo> arr2 = TavoloDAO.tavoliOccpuati();
		System.out.println("Numero tavoli occupati : " + arr2.size());
		arr2.forEach(t -> System.out.println("Tavolo n : " + t.getNumeroTavolo() + " Numero posti : " + t.getNumeroMaxCoperti() + " \n"));
	}
	
}
