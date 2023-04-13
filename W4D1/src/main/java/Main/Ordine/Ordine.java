package Main.Ordine;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Articolo.ArticoloMenu;
import Configuration.Configuration;
import Enum.StatoOrdine;
import Main.W4D1Application;
import Main.tavolo.Tavolo;

@Entity
@Table(name = "ordini")
public class Ordine {

	@Id
	private Long numeroOrdine;
	
	private List<ArticoloMenu> ordinazione;
	
	@Enumerated(EnumType.STRING)
	private StatoOrdine stato;
	private Integer numeroCoperti;
	private int oraAcquisizione;
	@OneToOne(mappedBy = "ordine")
	private Tavolo tavolo;
	
	public Ordine(Long numeroOrdine, List<ArticoloMenu> ordinazione, StatoOrdine stato, Integer numeroCoperti,
			int oraAcquisizione, Tavolo tavolo) {
		super();
		this.numeroOrdine = numeroOrdine;
		this.ordinazione = ordinazione;
		this.stato = stato;
		this.numeroCoperti = numeroCoperti;
		this.oraAcquisizione = oraAcquisizione;
		this.tavolo = tavolo;
	}

	public Ordine() {
		super();
	}

	public Long getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine() {
		
		Long num = System.currentTimeMillis();
		Random rnum = new Random(num);
		Long norder = Math.abs(rnum.nextLong()) % 9000000000000L + 1000000000000L;
		norder *= 10L;
		this.numeroOrdine = norder;
	}

	public List<ArticoloMenu> getOrdinazione() {
		return ordinazione;
	}

	public void setOrdinazione(List<ArticoloMenu> ordinazione) {
		this.ordinazione = ordinazione;
	}

	public StatoOrdine getStato() {
		return stato;
	}

	public void setStato(StatoOrdine stato) {
		this.stato = stato;
	}

	public Integer getNumeroCoperti() {
		return numeroCoperti;
	}

	public void setNumeroCoperti() {
		System.out.println("Inserisci il numero di coperti");
		String numcoperti = W4D1Application.s.nextLine();
		
		try {
			int ncoperti = Integer.parseInt(numcoperti);
			if(ncoperti <= this.tavolo.getNumeroMaxCoperti()) {
				this.numeroCoperti = ncoperti;
			} else {
				System.out.println("Il numero di coperti non può essere maggiore dei posti disponibili!");
			}		
		} catch(Exception e) {System.out.println("Error " + e);}	
	}

	public int getOraAcquisizione() {
		return oraAcquisizione;
	}

	public void setOraAcquisizione() {
		
		Date date = new Date();   
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);   
		this.oraAcquisizione = calendar.get(Calendar.HOUR_OF_DAY); 
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	
	public static Ordine generaOrdine() {
		
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Configuration.class);
				
		Ordine o = (Ordine) appContext.getBean("ordine");
				
				appContext.close();
				return o;
				
			}
	
	public static void salvaOrdine(List<ArticoloMenu> arr, Tavolo t) {
		
		Ordine o = generaOrdine();
		o.setOrdinazione(arr);
		o.setNumeroCoperti();
		o.setNumeroOrdine();
		o.setOraAcquisizione();
		o.setTavolo(t);
		o.setStato(StatoOrdine.IN_CORSO);
		OrdineDAO.saveOrdine(o);
	}
	
	public static void showAllOrders () {
		
		List<Ordine> arr = OrdineDAO.allOrdini();
		arr.forEach(o -> System.out.println("Ordine n: " + o.getNumeroOrdine() + " Stato: " + o.getStato() + " Tavolo: " + o.getTavolo()));
	}
	
}
