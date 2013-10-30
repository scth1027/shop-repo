package de.shop.kundenverwaltung.domain;

import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import de.shop.bestellverwaltung.domain.Bestellung;

@XmlRootElement
public class Privatkunde extends AbstractKunde 
{
	

	//Attribute
	private String vorname;
	private String nachname;
	
	//Getter & Setter
	public String getVorname()
	{
		return vorname;
	}
	
	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}
	
	public String getNachname()
	{
		return nachname;
	}
	
	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}


	//Methoden
	@Override
	public String toString() {
		return "Privatkunde [vorname=" + vorname + ", nachname=" + nachname
				+ super.toString() + "]";
	}
	

	


	
	

}
