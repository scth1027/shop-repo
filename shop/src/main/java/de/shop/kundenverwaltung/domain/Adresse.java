package de.shop.kundenverwaltung.domain;

public class Adresse 
{
	//Attribute
	private Long id;
	private String plz;
	private String ort;
	
	//Konstruktor
	public Adresse(Long id, String plz, String ort) 
	{
		super();
		this.id = id;
		this.plz = plz;
		this.ort = ort;
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
	
	public String getPlz()
	{
		return plz;
	}
	
	public void setPlz(String plz)
	{
		this.plz = plz;
	}
	
	public String getOrt()
	{
		return ort;
	}
	
	public void setOrt(String ort)
	{
		this.ort = ort;
	}
	
	//Methoden
	@Override
	public String toString() 
	{
		return "Adresse [id=" + id + ", plz=" + plz + ", ort=" + ort + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
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
		Adresse other = (Adresse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} else if (!plz.equals(other.plz))
			return false;
		return true;
	}
	
	
	
	
}
