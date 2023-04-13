package com.spring_intro_config.configuration;

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
public class Test {

	private String name;
	
	public String saluta() {
		return "Ciao " + name; 
	}

	
}
