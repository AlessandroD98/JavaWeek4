package com.spring_intro_config.configuration_Component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component("TestComponent")
@Scope("prototype")
public class TestComponent {

	private String name;
	
	public String saluta() {
		return "Ciao " + name; 
	}

	
}
