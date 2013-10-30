package de.shop.kundenverwaltung.domain;

import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import de.shop.bestellverwaltung.domain.Bestellung;

@XmlRootElement
public class Firmenkunde extends AbstractKunde
{
	//Attribute
	private String firmenname;
	private String ansprechpartner;
	
	//Getter & Setter
	public String getFirmenname() 
	{
		return firmenname;
	}
	
	public void setFirmenname(String firmenname)
	{
		this.firmenname = firmenname;
	}

	public String getAnsprechpartner() 
	{
		return ansprechpartner;
	}

	public void setAnsprechpartner(String ansprechpartner) 
	{
		this.ansprechpartner = ansprechpartner;
	}
	

	//Methoden
	@Override
	public String toString() {
		return "Firmenkunde [firmenname=" + firmenname + ", ansprechpartner="
				+ ansprechpartner +  super.toString() +"]";
	}
	

	
	
	
	
	

}
