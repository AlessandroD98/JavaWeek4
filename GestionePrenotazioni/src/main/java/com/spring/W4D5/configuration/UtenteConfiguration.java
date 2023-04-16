package com.spring.W4D5.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.spring.W4D5.model.Utente;


@Configuration
public class UtenteConfiguration {

	@Bean("FakeUtente")
	@Scope("prototype")
	public Utente fakeUtente() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Utente u = new Utente();
		u.setName(fake.name().firstName());
		u.setLastname(fake.name().lastName());
		u.setEmail(u.getName() + "." + u.getLastname() + "@gmail.com");
		u.setUsername(u.getName().toLowerCase() + "." + u.getLastname().toLowerCase() + fake.number().numberBetween(10, 100));
		return u;
	}
	
}
