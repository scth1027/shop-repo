package de.shop.artikelverwaltung.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;


public class Artikel {
	
	//Attribute
	private Long id;
	private String bezeichnung;
	private BigDecimal preis;
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
	public BigDecimal getPreis() {
		return preis;
	}
	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	public String getHersteller() {
		return hersteller;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
		
		//Methoden
		@Override
		public String toString() {
			return "Artikel [id=" + id + ", bezeichung=" + bezeichnung + ", preis=" + preis 
					+ ", hersteller=" + hersteller + "]";
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
			result = prime * result + ((hersteller == null) ? 0 : hersteller.hashCode());
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
			final Artikel other = (Artikel) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} 
			else if (!id.equals(other.id))
				return false;
			if (bezeichnung == null) {
				if (other.bezeichnung != null)
					return false;
			} 
			else if (!bezeichnung.equals(other.bezeichnung))
				return false;
			if (hersteller == null) {
				if (other.hersteller != null)
					return false;
			} 
			else if (!hersteller.equals(other.hersteller))
				return false;
			return true;
		}
}
