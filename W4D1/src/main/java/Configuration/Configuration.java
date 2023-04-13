package Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import Articolo.Drinks;
import Articolo.Franchise;
import Articolo.Pizza;
import Articolo.Topping;
import Main.Ordine.Ordine;
import Main.tavolo.Tavolo;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	@Scope("prototype")
	public Pizza pizza() {
		return new Pizza();
	}
	
	@Bean
	@Scope("prototype")
	public Drinks drinks() {
		return new Drinks();
	}
	
	@Bean
	@Scope("prototype")
	public Franchise franchise() {
		return new Franchise();
	}
	
	@Bean
	@Scope("prototype")
	public Topping topping() {
		return new Topping();
	}
	
	@Bean
	@Scope("prototype")
	public Tavolo tavolo() {
		return new Tavolo();
	}
	
	@Bean
	@Scope("prototype")
	public Ordine ordine() {
		return new Ordine();
	}
}
