package com.spring.W4D5.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.spring.W4D5.model.Edificio;
import com.spring.W4D5.model.Postazione;

@Configuration
public class PostazioneConfiguration {

	@Bean("FakePostazione")
	@Scope("prototype")
	public Postazione fakePostazione() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Postazione p = new Postazione();
		p.setNumeroMaxOccupanti(fake.number().numberBetween(4, 20));
		p.setStato(false);
		return p;
	}
	
}
