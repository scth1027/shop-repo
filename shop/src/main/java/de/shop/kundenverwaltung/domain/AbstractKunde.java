package de.shop.kundenverwaltung.domain;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public abstract class AbstractKunde
{
	//Attribute
	private Long id;
	private String email;
	private Adresse adresse;
	
	//@XmlTransient
	//private List<Bestellung> bestellungen;
	//ToDo: Klasse Bestellung muss noch angelegt werden
	
	private URI bestellungenUri;

	//Konstruktor
	public AbstractKunde(Long id, String email, Adresse adresse, URI bestellungenUri) 
	{
		super();
		this.setId(id);
		this.setEmail(email);
		this.setAdresse(adresse);
		this.setBestellungenUri(bestellungenUri);
	}

	//Getter & Setter
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Adresse getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(Adresse adresse) 
	{
		this.adresse = adresse;
	}

	public URI getBestellungenUri() 
	{
		return bestellungenUri;
	}

	public void setBestellungenUri(URI bestellungenUri) 
	{
		this.bestellungenUri = bestellungenUri;
	}

	//Methoden
	@Override
	public String toString() 
	{
		return "AbstractKunde [id=" + id + ", email=" + email + ", adresse="
				+ adresse + ", bestellungenUri=" + bestellungenUri + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result
				+ ((bestellungenUri == null) ? 0 : bestellungenUri.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractKunde other = (AbstractKunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (bestellungenUri == null) {
			if (other.bestellungenUri != null)
				return false;
		} else if (!bestellungenUri.equals(other.bestellungenUri))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
