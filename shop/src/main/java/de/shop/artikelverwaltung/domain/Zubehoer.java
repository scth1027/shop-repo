package de.shop.artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Zubehoer extends AbstractArtikel {

	private static final long serialVersionUID = -104960093004088594L;
	
	private String artikeldetails_z;

	public String getArtikeldetails_z() {
		return artikeldetails_z;
	}

	public void setArtikeldetails_z(String artikeldetails_z) {
		this.artikeldetails_z = artikeldetails_z;
	}

	@Override
	public String toString() {
		return "Zubehör [" + super.toString() + ", Artikeldetails:" + artikeldetails_z + "]";
	}
}
