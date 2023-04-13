package Articolo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Configuration.Configuration;
import Main.W4D1Application;

public class Franchise extends ArticoloMenu {
	
	public static void setName() {}
	
	public static void setprice() {}
	
	public static void selectFranchise(List<ArticoloMenu> arr) {
		Boolean exit = false;
		while(!exit) {
			System.out.println(">> Acquista uno dei seguenti prodotti firmati | 0 per uscire \n"
					+ "\n 1 Shirt | 21.99€"
					+ "\n 2 Mug | 4.99€");
			int scelta =  W4D1Application.s.nextInt();
			W4D1Application.s.nextLine();
			switch(scelta) {
			case 0 :
				exit = true;
				break;
			case 1:
				Franchise f = createFranchise();
				arr.add(f);
				break;
			case 2:
				Franchise f1 = createFranchise();
				arr.add(f1);
				break;
				default: System.out.println("Seleziona un valore valido");
			}
		}
	}
	
	public static Franchise createFranchise() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Configuration.class);
		
		Franchise f = (Franchise) appContext.getBean("franchise");
		
		appContext.close();
		return f;
	}
}