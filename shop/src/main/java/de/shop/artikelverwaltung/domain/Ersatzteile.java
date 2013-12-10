package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ersatzteile extends AbstractArtikel {

	private static final long serialVersionUID = 928059348953288434L;

	private String artikeldetailse;

	public String getArtikeldetailse() {
		return artikeldetailse;
	}

	public void setArtikeldetailse(String artikeldetailse) {
		this.artikeldetailse = artikeldetailse;	
	}

	@Override
	public String toString() {
		return "Ersatzteile [" + super.toString() + ", Artikeldetails:" + artikeldetailse + "]";
	}

}
