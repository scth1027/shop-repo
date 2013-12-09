package de.shop.kundenverwaltung.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


public class Adresse implements Serializable {
	private static final long serialVersionUID = -3029272617931844501L;
	
	
	private Long id;
	
	@NotNull(message = "{adresse.plz.NotNull}")
	@Pattern(regexp = "\\d{5}", message = "{adresse.plz.pattern}")
	private String plz;
	
	@NotNull(message = "{adresse.ort.NotNull}")
	@Size(min = 2, max = 32, message = "{adresse.ort.size}")
	private String ort;
	
	@XmlTransient
	private AbstractKunde kunde;
	
	//Getter & Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public AbstractKunde getKunde() {
		return kunde;
	}
	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}
	
	//Methoden
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
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
		final Adresse other = (Adresse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		}
		else if (!ort.equals(other.ort))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		}
		else if (!plz.equals(other.plz))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", plz=" + plz + ", ort=" + ort + "]";
	}
}
