package de.shop.bestellverwaltung.domain;

import static de.shop.util.Constants.KEINE_ID;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;






@XmlRootElement
@Entity

@Table(indexes = {
	@Index(columnList = "kunde_fk")
})
public class Bestellung implements Serializable {
	
	private static final long serialVersionUID = -97562639100824340L;
	
	//Attribute
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@ManyToOne 
	@JoinColumn (name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	@XmlTransient
	private AbstractKunde kunde;
	
	@Transient
	private URI kundeUri;
	
	@NotNull(message = "{bestellung.date.NotNull}")
	@Valid
	private Date bestelldatum;
	
	@NotNull(message = "{bestellung.gesamtpreis.NotNull}")
	private BigDecimal gesamtpreis;
	
	@NotNull(message = "{bestellung.adresse.NotNull}")
	@Valid
	private Adresse lieferadresse;
	
	private boolean status;

//TODO Kommentare entfernen sobald Artikel implementiert ist	
//	@Valid
//	@OneToMany(fetch = EAGER, cascade = { PERSIST, REMOVE })
//	@JoinColumn(name = "bestellung_fk", nullable = false)
//	@NotEmpty 
//	private List<Posten> posten;
	
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

//TODO Kommentare entfernen sobald Artikel implementiert ist	
//	public List<Posten> getPosten() {
//		return posten;
//	}
//	public void setPosten(List<Posten> posten) {
//		this.posten = posten;
//	}
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
				+ lieferadresse + ", status=" + status + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((lieferadresse == null) ? 0 : lieferadresse.hashCode());
		//result = prime * result + ((status == null) ? 0 : status.hashCode());

		return result;
	}
	


	
}
