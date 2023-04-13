package Main.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import JpaUtil.JpaUtil;

public class TavoloDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	  public static void saveTavolo(Tavolo t) {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		    }
	
	  public static Tavolo findTavolo (Long l) {
		  
		  Tavolo t = null;
		  try {			  
			  em.getTransaction().begin();
			  Tavolo T = em.find(Tavolo.class, l);
			  em.getTransaction().commit();
			  if(T == null) {
				  System.out.println("Valore di ritorno Null");
			  } else {
				  t = T;
			  }
		  } catch (Exception e) {
			  System.out.println("Error " + e);
		  }
		  return t;
	  }
	  
	  public static List<Tavolo> tavoliOccpuati() {
		  
		 Query q = em.createQuery("SELECT t FROM Tavolo t WHERE t.stato = true");
		 List<Tavolo> arr = q.getResultList();
		 return arr;
	  }
	  
	  public static List<Tavolo> tavoliLiberi() {
		  
		  Query q = em.createQuery("SELECT t FROM Tavolo t WHERE t.stato = false");
			 List<Tavolo> arr = q.getResultList();
			 return arr;
	  }
}