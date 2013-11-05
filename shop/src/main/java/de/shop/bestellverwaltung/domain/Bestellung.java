package de.shop.bestellverwaltung.domain;

import java.util.Date;
import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.artikelverwaltung.Artikel;




@XmlRootElement
public class Bestellung {
	
	//Attribute
	private Long id;
	private AbstractKunde kunde;
	private Date bestelldatum;
	private double gesamtpreis;
	private Adresse lieferadresse;
	private boolean status;
	@XmlTransient
	private List<Artikel> artikel;
	private URI artikelUri;
	private URI kundeUri;
	
	//Getter & Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public de.shop.kundenverwaltung.domain.AbstractKunde getKunde() {
		return kunde;
	}
	public void setKunde(de.shop.kundenverwaltung.domain.AbstractKunde kunde) {
		this.kunde = kunde;
	}
	public Date getBestelldatum() {
		return bestelldatum;
	}
	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}
	public double getGesamtpreis() {
		return gesamtpreis;
	}
	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}
	public Adresse getLieferadresse() {
		return lieferadresse;
	}
	public void setLieferadresse(Adresse lieferadresse) {
		this.lieferadresse = lieferadresse;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<Artikel> getArtikel() {
		return artikel;
	}
	public void setArtikel(List<Artikel> artikel) {
		this.artikel = artikel;
	}
	public URI getArtikelUri() {
		return artikelUri;
	}
	public void setArtikelUri(URI artikelUri) {
		this.artikelUri = artikelUri;
	}
	public URI getKundeUri() {
		return kundeUri;
	}
	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}
	

	
	//Methoden
	@Override
	public String toString() 
	{
		return "Bestellung [id=" + id + ", kunde=" + kunde + ", bestelldatum=" + bestelldatum + ", gesamtpreis= " + gesamtpreis + ", lieferadresse="
				+ lieferadresse + ", status=" + status + ", artikel=" 
				+ artikel + ", artikelUri=" + artikelUri + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((lieferadresse == null) ? 0 : lieferadresse.hashCode());
		//result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((artikelUri == null) ? 0 : artikelUri.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestellung other = (Bestellung) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;
		if (lieferadresse == null) {
			if (other.lieferadresse != null)
				return false;
		} else if (!lieferadresse.equals(other.lieferadresse))
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (artikelUri == null) {
			if (other.artikelUri != null)
				return false;
		} else if (!artikelUri.equals(other.artikelUri))
			return false;
		return true;
	}
	
}
