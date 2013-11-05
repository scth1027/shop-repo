package de.shop.artikelverwaltung;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Artikel {
	
	//Attribute
	private Long id;
	private String bezeichnung;
	private Double preis;
	private String hersteller;
	
	//Getter & Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBezeichung() {
		return bezeichnung;
	}
	public void setBezeichung(String bezeichung) {
		this.bezeichnung = bezeichung;
	}
	public Double getPreis() {
		return preis;
	}
	public void setPreis(Double preis) {
		this.preis = preis;
	}
	public String getHersteller() {
		return hersteller;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
	
	//Konstruktor
		public Artikel(Long id, String bezeichnung, Double preis, String hersteller) 
		{
			super();
			this.id = id;
			this.bezeichnung = bezeichnung;
			this.preis = preis;
			this.hersteller= hersteller;
		}
		
		//Methoden
		@Override
		public String toString() 
		{
			return "Artikel [id=" + id + ", bezeichung=" + bezeichnung + ", preis=" + preis + ", hersteller="+ hersteller +"]";
		}
		
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
			result = prime * result + ((hersteller == null) ? 0 : hersteller.hashCode());
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
			Artikel other = (Artikel) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (bezeichnung == null) {
				if (other.bezeichnung != null)
					return false;
			} else if (!bezeichnung.equals(other.bezeichnung))
				return false;
			if (hersteller == null) {
				if (other.hersteller != null)
					return false;
			} else if (!hersteller.equals(other.hersteller))
				return false;
			return true;
		}
}
