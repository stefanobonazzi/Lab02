package it.polito.tdp.alien.model;

import java.util.*;

public class Dizionario {

	Map<String, List<String>> dizionario;
	
	public Dizionario() {
		this.dizionario = new TreeMap<String, List<String>>();
	}
	
	public void addParola(String aliena, String terrestre) {
		List<String> l ;
		
		if((l = this.dizionario.get(aliena)) != null) {
			l.add(terrestre);
			this.dizionario.put(aliena, l);
		} else {
			l = new LinkedList<String>();
			l.add(terrestre);
			this.dizionario.put(aliena, l);
		}
	}
	
	public List<String> translate(String aliena, boolean wildcard) {
		if(!dizionario.containsKey(aliena))
			throw new NullPointerException();
		
		if(!wildcard)
			return this.dizionario.get(aliena);
	
		aliena = aliena.replace('?', '.');
		List<String> l = new LinkedList<String>();
		
		for(String s: dizionario.keySet())
			if(s.matches(aliena))
				l.addAll(dizionario.get(s));
		
		return l;
	}

}
