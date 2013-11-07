package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class Ersatzteile extends AbstractArtikel {
	

	private static final long serialVersionUID = 928059348953288434L;

	private String artikeldetails_e;

	public String getArtikeldetails_e() {
		return artikeldetails_e;
	}

	public void setArtikeldetails(String artikeldetails) {
		this.artikeldetails_e = artikeldetails_e;	
	}

	@Override
	public String toString() {
		return "Ersatzteile [" + super.toString() + ", Artikeldetails:" + artikeldetails_e + "]";
	}
	
}
