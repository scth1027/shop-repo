package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Zubehoer extends AbstractArtikel {

	private static final long serialVersionUID = -104960093004088594L;
	
	private String artikeldetailsz;

	public String getArtikeldetailsz() {
		return artikeldetailsz;
	}

	public void setArtikeldetailsz(String artikeldetailsz) {
		this.artikeldetailsz = artikeldetailsz;
	}

	@Override
	public String toString() {
		return "Zubehör [" + super.toString() + ", Artikeldetails:" + artikeldetailsz + "]";
	}
}
