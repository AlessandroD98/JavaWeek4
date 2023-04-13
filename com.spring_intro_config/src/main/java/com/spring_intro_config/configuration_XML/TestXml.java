package com.spring_intro_config.configuration_XML;

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
public class TestXml {

	private String name;
	
	public String saluta() {
		return "Ciao " + name; 
	}

	
}
