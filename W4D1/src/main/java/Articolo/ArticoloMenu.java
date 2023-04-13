package Articolo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class ArticoloMenu {

	
	protected double price;
	private String name;
	
	public static void toString(ArticoloMenu a) {
		if(a instanceof Pizza) {
			Pizza p = (Pizza) a;
			System.out.println(p.getName() + " Calorie " + p.getCalories() + " Price " + p.getPrice() + "€");
		} else if (a instanceof Drinks) {
			Drinks d = (Drinks) a;
			System.out.println(d.getName() + " Calorie " + d.getCalories() + " Price " + d.getPrice()+ "€");
		} else if (a instanceof Franchise) {
			Franchise f = (Franchise) a;
			System.out.println(f.getName() + " Calorie " + f.getPrice()+ "€");
		}else {
			System.out.println("Articolo non supportato");
		}
	}
	
}
