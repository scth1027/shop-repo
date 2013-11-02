package de.shop.kundenverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Privatkunde extends AbstractKunde 
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4638753784585279152L;
	//Attribute
	private String vorname;

	
	//Getter & Setter
	public String getVorname()
	{
		return vorname;
	}
	
	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	//Methoden
	@Override
	public String toString() {
		return "Privatkunde [vorname=" + vorname 
				+ super.toString() + "]";
	}
	

	


	
	

}
