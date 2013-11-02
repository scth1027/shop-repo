package de.shop.kundenverwaltung.domain;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Firmenkunde extends AbstractKunde
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1754258788795959211L;
	//Attribute
	
	private String ansprechpartner;
	
	//Getter & Setter

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
		return "ansprechpartner="
				+ ansprechpartner +  super.toString() +"]";
	}
	

	
	
	
	
	

}
