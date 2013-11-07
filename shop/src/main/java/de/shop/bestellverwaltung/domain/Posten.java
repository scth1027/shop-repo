package de.shop.bestellverwaltung.domain;

import de.shop.artikelverwaltung.domain.Artikel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Posten {
	
	private Artikel artikel;
	private Long anzahl;
	
	public Artikel getArtikel() {
		return artikel;
	}
	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}
	public Long getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(Long anzahl) {
		this.anzahl = anzahl;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anzahl == null) ? 0 : anzahl.hashCode());
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posten other = (Posten) obj;
		if (anzahl == null) {
			if (other.anzahl != null)
				return false;
		} else if (!anzahl.equals(other.anzahl))
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Posten [artikel=" + artikel + ", anzahl=" + anzahl + "]";
	}

}
