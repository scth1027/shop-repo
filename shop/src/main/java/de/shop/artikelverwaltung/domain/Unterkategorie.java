package de.shop.artikelverwaltung.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

public class Unterkategorie implements Serializable {

	private static final long serialVersionUID = 5028963284106923059L;
	
	@XmlTransient
    
	//private AbstractArtikel artikel;
    
    private Long klassenId;
    private String name;
    private String beschreibung;
	
    //public AbstractArtikel getArtikel() {
		//return artikel;
//	}
//	public void setArtikel(AbstractArtikel artikel) {
//		this.artikel = artikel;
//	}
	public Long getKlassenId() {
		return klassenId;
	}
	public void setKlassenId(Long klassenId) {
		this.klassenId = klassenId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beschreibung == null) ? 0 : beschreibung.hashCode());
		result = prime * result
				+ ((klassenId == null) ? 0 : klassenId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Unterkategorie other = (Unterkategorie) obj;
		if (beschreibung == null) {
			if (other.beschreibung != null)
				return false;
		} else if (!beschreibung.equals(other.beschreibung))
			return false;
		if (klassenId == null) {
			if (other.klassenId != null)
				return false;
		} else if (!klassenId.equals(other.klassenId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Unterkategorie [" + super.toString() + ",klassenId=" + klassenId + ", name=" + name
				+ ", beschreibung=" + beschreibung + "]";
	}
	
	

}
