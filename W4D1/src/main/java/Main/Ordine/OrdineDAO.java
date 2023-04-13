package Main.Ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import JpaUtil.JpaUtil;

public class OrdineDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	  public static void saveOrdine(Ordine o) {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		    }
	
	  static public List<Ordine> allOrdini() {
		  
		  Query q = em.createQuery("SELECT o FROM Ordine o");
		  List<Ordine> arr = q.getResultList();
		  return arr;
		  
	  }
}
