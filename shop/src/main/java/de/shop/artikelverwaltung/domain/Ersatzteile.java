package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class Ersatzteile {
	
	private static final long serialVersionUID = 1L;

	private String artikeldetails;

	public String getArtikeldetails() {
		return artikeldetails;
	}

	public void setArtikeldetails(String artikeldetails) {
		this.artikeldetails = artikeldetails;
		
		
	}

	@Override
	public String toString() {
		return "Ersatzteile [" + super.toString() + ", Artikeldetails:" + artikeldetails + "]";
	}
	
}
