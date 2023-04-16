package com.spring.W4D5.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.spring.W4D5.model.Edificio;

@Configuration
public class EdificioConfiguration {

	@Bean("FakeEdificio")
	@Scope("prototype")
	public Edificio fakeEdificio() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Edificio e = new Edificio();
		e.setCittà(fake.address().cityName().toUpperCase());
		e.setIndirizzo(fake.address().fullAddress());
		e.setNome(fake.funnyName().name());
		return e;
	}
	
}
