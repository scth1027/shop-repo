package de.shop.artikelverwaltung.domain;


import javax.xml.bind.annotation.XmlRootElement;

import java.util.Set;

@XmlRootElement
public class Fahrrad extends AbstractArtikel {
	

	private static final long serialVersionUID = 2951455983440524459L;
	
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