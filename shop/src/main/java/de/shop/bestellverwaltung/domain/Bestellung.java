package de.shop.bestellverwaltung.domain;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;






@XmlRootElement
public class Bestellung implements Serializable {
	
	private static final long serialVersionUID = -97562639100824340L;
	
	//Attribute
	@NotNull
	private Long id;
	
	@NotNull(message = "{bestellung.abstractkunde.NotNull}")
	@Valid
	private AbstractKunde kunde;
	
	@NotNull(message = "{bestellung.date.NotNull}")
	@Valid
	private Date bestelldatum;
	
	@NotNull(message = "{bestellung.gesamtpreis.NotNull}")
	private BigDecimal gesamtpreis;
	
	@NotNull(message = "{bestellung.adresse.NotNull}")
	@Valid
	private Adresse lieferadresse;
	
	private boolean status;
	
	//@NotNull
	@Valid
	@XmlTransient
	private List<Posten> posten;
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
	public BigDecimal getGesamtpreis() {
		return gesamtpreis;
	}
	public void setGesamtpreis(BigDecimal gesamtpreis) {
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
	public List<Posten> getPosten() {
		return posten;
	}
	public void setPosten(List<Posten> posten) {
		this.posten = posten;
	}
	public URI getKundeUri() {
		return kundeUri;
	}
	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}
	

	
	//Methoden
	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", kunde=" + kunde + ", bestelldatum=" + bestelldatum 
				+ ", gesamtpreis= " + gesamtpreis + ", lieferadresse="
				+ lieferadresse + ", status=" + status + ", posten=" 
				+ posten + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((lieferadresse == null) ? 0 : lieferadresse.hashCode());
		//result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((posten == null) ? 0 : posten.hashCode());
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
		final Bestellung other = (Bestellung) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		}
		else if (!kunde.equals(other.kunde))
			return false;
		if (lieferadresse == null) {
			if (other.lieferadresse != null)
				return false;
		} 
		else if (!lieferadresse.equals(other.lieferadresse))
			return false;
		if (posten == null) {
			if (other.posten != null)
				return false;
		} 
		else if (!posten.equals(other.posten))
			return false;
		return true;
	}
	
}
