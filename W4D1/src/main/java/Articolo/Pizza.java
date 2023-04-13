package Articolo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Configuration.Configuration;
import Main.W4D1Application;


public class Pizza extends ArticoloMenu {

	private Integer calories;
	private List<Topping> toppings;
	
	public Pizza() {
		this.price = 4.99;
		this.calories = 1104;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	
	public List<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(List<Topping> toppings) {
		this.toppings = toppings;
	}
	
public static void pizzeOption(List<ArticoloMenu> arr) {
		
		Boolean exit = false;
		while(!exit) {	
			System.out.println("Scegli una delle nostre pizze o creane una! | 0 per uscire"
					+ "\n 1 Pizza Margherita (tomato, cheese) | Calories 1104 | Price  4.99€"
					+ "\n 2 Hawailan Pizza (tomato, cheese, ham, pineapple) | Calories 1024 | Price 6.49€"
					+ "\n 3 Salami Pizza (tomato, cheese, salami) | Calories 1160 | Price 5.99€"
					+ "\n 4 Crea la tua pizza");
			int scelta = W4D1Application.s.nextInt();
			W4D1Application.s.nextLine();
			switch(scelta) {
			case 0:
				exit = true;
				break;
			case 1:
				Pizza p = createPiazza();
				p.setName("Pizza Margherita");
				arr.add(p);
				System.out.println( p.getName() + " aggiunta all'ordine");
				break;
			case 2:
				Pizza p1 = createPiazza();
				p1.setCalories(1024);
				p1.setName("Hawailan Pizza");
				p1.setPrice(6.49);
				arr.add(p1);
				System.out.println( p1.getName() + " aggiunta all'ordine");
				break;
			case 3:
				Pizza p2 = createPiazza();
				p2.setCalories(1160);
				p2.setName("Salami Pizza");
				p2.setPrice(5.99);
				arr.add(p2);
				System.out.println( p2.getName() + " aggiunta all'ordine");
				break;
			case 4:
				Pizza p3 = createPiazza();
				p3.setName("Pizza personalizzata");
				List<Topping> Topp = personalizedPizza();
				p3.setToppings(Topp);
				arr.add(p3);
				break;
			default: System.out.println("Utilizza un valore valido");
			}
		}
	}
	
	public static Pizza createPiazza() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Configuration.class);
		
		Pizza p = (Pizza) appContext.getBean("pizza");
		
		appContext.close();
		return p;
	}
	
	public static List<Topping> personalizedPizza() {
		
		List<Topping> toppings = new ArrayList<>();
		Boolean exit = false;
		while(!exit) {	
			System.out.println("Segli cosa vuoi aggiungere alla tua pizza (Base margherita)| 0 per uscire"
					+ "\n 1 Cheese | Calories 92 | Price 0.69€"
					+ "\n 2 Ham | Calories 35 | Price 0.99€"
					+ "\n 3 Onions | Calories 22 | Price 0.69€"
					+ "\n 4 Pineapple | Calories 24 | Price 0.79€"
					+ "\n 5 Salami | Calories 86 | Price 0.99€");
			int scelta = W4D1Application.s.nextInt();
			W4D1Application.s.nextLine();
			switch(scelta) {
			case 0:
				exit = true;
				break;
			case 1:
				Topping t = Topping.createTopping();
			t.setName("");
			t.setPrice(0.69);
			t.setCalories(92);
			toppings.add(t);
				break;
			case 2:
				Topping t1 = Topping.createTopping();
				t1.setName("");
				t1.setPrice(0.99);
				t1.setCalories(35);
				toppings.add(t1);
				break;
			case 3:
				Topping t2 = Topping.createTopping();
				t2.setName("");
				t2.setPrice(0.69);
				t2.setCalories(22);
				toppings.add(t2);
				break;
			case 4:
				Topping t3 = Topping.createTopping();
				t3.setName("");
				t3.setPrice(0.79);
				t3.setCalories(24);
				toppings.add(t3);
				break;
			case 5:
				Topping t4 = Topping.createTopping();
				t4.setName("");
				t4.setPrice(0.99);
				t4.setCalories(86);
				toppings.add(t4);
				break;
			default: System.out.println("Utilizza un valore valido");
			}
		}
		return toppings;
	}


}