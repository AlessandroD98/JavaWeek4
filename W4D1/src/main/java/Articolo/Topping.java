package Articolo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Configuration.Configuration;

public class Topping extends ArticoloMenu{

	private Integer calories;
	
	public static Topping createTopping() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Configuration.class);
		
		Topping t = (Topping) appContext.getBean("topping");
		
		appContext.close();
		return t;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}
}
