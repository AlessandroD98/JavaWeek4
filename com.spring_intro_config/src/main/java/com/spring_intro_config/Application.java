package com.spring_intro_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring_intro_config.configuration.ConfigurationTest;
import com.spring_intro_config.configuration.Test;
import com.spring_intro_config.configuration_Component.TestComponent;
import com.spring_intro_config.configuration_XML.TestXml;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		//Configurazione 1
		//Configurazione dei beans tramite una class Configuration
		//configWith_Beans1();
		//Configurazione dei beans tramite file xml
		//Configurazione 2
		//configWith_XML_1();
		//Configurazione 3
		//Configurazione dei beans tramite annotation @Components("name")
		configWith_Components_1();
	}

	public static void configWith_Beans1() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(ConfigurationTest.class);
		
		//Recupero Bean
		Test t1 = (Test) appContext.getBean("test");
		System.out.println(t1.saluta());
		
		//Recupero Bean con parametri
				Test t2 = (Test) appContext.getBean("paramtest", "Param Test");
				System.out.println(t2.saluta());
				
				 appContext.close();
	}
	
	public static void configWith_XML_1() {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("Beans.xml");
		
		//Recupero Bean
		TestXml t1 = (TestXml) appContext.getBean("TestXml"); //Nelle parentesi va l'id inserito nel file xml
		System.out.println(t1.saluta());
		
		appContext.close();
	}
	
	public static void configWith_Components_1() {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		
		appContext.scan("com.spring_intro_config.configuration_Component");
		appContext.refresh();
		
		//Recupero Bean
		
		TestComponent t1 = (TestComponent) appContext.getBean("TestComponent");
		t1.setName("Test");
		System.out.println(t1.saluta());
		
		//Recupero Bean con parametri
		TestComponent t2 = (TestComponent) appContext.getBean("TestComponent", "Param Test");
		System.out.println(t2.saluta());
		
		appContext.close();
	}
}