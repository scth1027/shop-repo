package de.shop.artikelverwaltung.domain;


import de.shop.artikelverwaltung.Artikel;
import de.shop.artikelverwaltung.Typ;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement
public class Fahrrad extends Artikel {
	
	public Fahrrad(Long id, String bezeichnung, Double preis, String hersteller) {
		super(id, bezeichnung, preis, hersteller);
		// TODO Auto-generated constructor stub
	}

	private Set<Typ> typ;
	private Set<Farbe> farbe;
	
	public Set<Typ> getTyp() {
		return typ;
	}
	public void setTyp(Set<Typ> typ) {
		this.typ = typ;
	}
	public Set<Farbe> getFarbe() {
		return farbe;
	}
	public void setFarbe(Set<Farbe> farbe) {
		this.farbe = farbe;
	}
	
	@Override
	public String toString() {
            return "Fahrrad [" + super.toString() + ", Typ:" + typ + ", Farbe:" + farbe + "]";
    }
}