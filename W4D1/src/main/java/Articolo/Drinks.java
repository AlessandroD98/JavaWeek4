package Articolo;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Configuration.Configuration;
import Main.W4D1Application;


public class Drinks extends ArticoloMenu {

	private Integer calories;

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	
	public static void selectDrink(List<ArticoloMenu> arr) {
		Boolean exit = false;
		while(!exit) {
			System.out.println(">> Ordina una bevanda | 0 per uscire \n"
					+ "\n 1 Lemonade (0,33l) | 128 Calories | 1.29€"
					+ "\n 2 Water (0.5l) | 0 Calories | 1.29€"
					+ "\n 3 Wine (0.75l, 13%) | 607 Calories | 7.49€");
			int scelta =  W4D1Application.s.nextInt();
			W4D1Application.s.nextLine();
			switch(scelta) {
			case 0 :
				exit = true;
				break;
			case 1:
				Drinks d = createDrink();
				d.setName("Lemonade");
				d.setPrice(1.29);
				d.setCalories(128);
				arr.add(d);
				break;
			case 2:
				Drinks d1 = createDrink();
				d1.setName("Water");
				d1.setPrice(1.29);
				d1.setCalories(0);
				arr.add(d1);
				break;
			case 3:
				Drinks d2 = createDrink();
				d2.setName("Wine");
				d2.setPrice(7.49);
				d2.setCalories(607);
				arr.add(d2);
				break;
				default: System.out.println("Seleziona un valore valido");
			}
		}
	}
	public static Drinks createDrink() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Configuration.class);
		
		Drinks d = (Drinks) appContext.getBean("drinks");
		
		appContext.close();
		return d;
	}
}
