package de.shop.kundenverwaltung.domain;

import static de.shop.util.Constants.KEINE_ID;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.Email;

import de.shop.bestellverwaltung.domain.Bestellung;


@XmlRootElement
@XmlSeeAlso({ Firmenkunde.class, Privatkunde.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@Type(value = Privatkunde.class, name = AbstractKunde.PRIVATKUNDE),
	@Type(value = Firmenkunde.class, name = AbstractKunde.FIRMENKUNDE) })

@Entity
@Inheritance
@DiscriminatorColumn(name = "art", length = 1)
@NamedQueries({
	@NamedQuery(name  = AbstractKunde.FIND_KUNDEN,
				query = "SELECT k"
			        + " FROM   AbstractKunde k"),
			        @NamedQuery(name  = AbstractKunde.FIND_KUNDEN_ORDER_BY_ID,
			        query = "SELECT   k"
					        + " FROM  AbstractKunde k"
			                + " ORDER BY k.id"),
		@NamedQuery(name  = AbstractKunde.FIND_IDS_BY_PREFIX,
			        query = "SELECT   k.id"
			                + " FROM  AbstractKunde k"
			                + " WHERE CONCAT('', k.id) LIKE :" + AbstractKunde.PARAM_KUNDE_ID_PREFIX
			                + " ORDER BY k.id"),
		@NamedQuery(name  = AbstractKunde.FIND_KUNDEN_BY_NACHNAME,
		            query = "SELECT k"
					        + " FROM   AbstractKunde k"
		            		+ " WHERE  UPPER(k.nachname) = UPPER(:" + AbstractKunde.PARAM_KUNDE_NACHNAME + ")"),
		@NamedQuery(name  = AbstractKunde.FIND_NACHNAMEN_BY_PREFIX,
	   	            query = "SELECT   DISTINCT k.nachname"
					        + " FROM  AbstractKunde k "
		            		+ " WHERE UPPER(k.nachname) LIKE UPPER(:"
		            		+ AbstractKunde.PARAM_KUNDE_NACHNAME_PREFIX + ")"),
	   	@NamedQuery(name  = AbstractKunde.FIND_KUNDE_BY_EMAIL,
	   	            query = "SELECT DISTINCT k"
	   			            + " FROM   AbstractKunde k"
	   			            + " WHERE  k.email = :" + AbstractKunde.PARAM_KUNDE_EMAIL),
	    @NamedQuery(name  = AbstractKunde.FIND_KUNDEN_BY_PLZ,
		            query = "SELECT k"
					        + " FROM  AbstractKunde k"
				            + " WHERE k.adresse.plz = :" + AbstractKunde.PARAM_KUNDE_ADRESSE_PLZ),

		@NamedQuery(name = AbstractKunde.FIND_PRIVATKUNDEN_FIRMENKUNDEN,
				    query = "SELECT k"
				            + " FROM  AbstractKunde k"
				    		+ " WHERE TYPE(k) IN (Privatkunde, Firmenkunde)")
	
	
})



@Table(name="kunde", indexes = @Index(columnList = "nachname"))
public abstract class AbstractKunde implements Serializable {
	private static final long serialVersionUID = 7401524595142572933L;
	
	private static final String PREFIX = "AbstractKunde.";
	public static final String FIND_KUNDEN = PREFIX + "findKunden";
	public static final String FIND_KUNDEN_ORDER_BY_ID = PREFIX + "findKundenOrderById";
	public static final String FIND_IDS_BY_PREFIX = PREFIX + "findIdsByPrefix";
	public static final String FIND_KUNDEN_BY_NACHNAME = PREFIX + "findKundenByNachname";
	public static final String FIND_NACHNAMEN_BY_PREFIX = PREFIX + "findNachnamenByPrefix";
	public static final String FIND_KUNDE_BY_EMAIL = PREFIX + "findKundeByEmail";
	public static final String FIND_KUNDEN_BY_PLZ = PREFIX + "findKundenByPlz";
	public static final String FIND_KUNDEN_BY_DATE = PREFIX + "findKundenByDate";
	public static final String FIND_PRIVATKUNDEN_FIRMENKUNDEN = PREFIX + "findPrivatkundenFirmenkunden";
	
	public static final String PARAM_KUNDE_ID = "kundeId";
	public static final String PARAM_KUNDE_ID_PREFIX = "idPrefix";
	public static final String PARAM_KUNDE_NACHNAME = "nachname";
	public static final String PARAM_KUNDE_NACHNAME_PREFIX = "nachnamePrefix";
	public static final String PARAM_KUNDE_ADRESSE_PLZ = "plz";
	public static final String PARAM_KUNDE_EMAIL = "email";
	
	public static final String GRAPH_BESTELLUNGEN = PREFIX + "bestellungen";
	
	public static final String PRIVATKUNDE = "P";
	public static final String FIRMENKUNDE = "F";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id=KEINE_ID;
	
	@NotNull(message = "{kunde.nachname.NotNull}")
	@Size(min = 2, max = 32, message = "{kunde.nachname.size}")
	@Pattern(regexp = "[A-ZÄÖÜ][a-zäöüß]+", message = "{kunde.nachname.pattern}")
	@Column(length = 32, nullable = false)
	private String nachname;
	
	@NotNull(message = "{kunde.email.NotNull}")
	@Email(message = "{kunde.email.pattern}")
	@Column(length = 64, nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "{kunde.adresse.NotNull}")
	@Valid
	@OneToOne(mappedBy = "kunde")
	private Adresse adresse;
	
	
	@OneToMany
	@JoinColumn(name = "kunde_fk", nullable = false)
	@OrderColumn(name = "idx", nullable = false)
	@XmlTransient
	private List<Bestellung> bestellungen;
	
	@Transient
	private URI bestellungenUri;

	//Getter & Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}
	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}

	public URI getBestellungenUri() {
		return bestellungenUri;
	}
	public void setBestellungenUri(URI bestellungenUri) {
		this.bestellungenUri = bestellungenUri;
	}
	
	//Methoden
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		final AbstractKunde other = (AbstractKunde) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AbstractKunde [id=" + id + ", nachname=" + nachname + ", email=" + email
			   + ", bestellungenUri=" + bestellungenUri + "]";
	}
}
