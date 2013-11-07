package de.shop.artikelverwaltung.domain;

import java.io.Serializable;
import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;


@XmlRootElement
@XmlSeeAlso({ Ersatzteile.class, Fahrrad.class, Zubehoer.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = Ersatzteile.class, name = AbstractArtikel.ERSATZTEILE),
        @Type(value = Fahrrad.class, name = AbstractArtikel.FAHRRAD),
        @Type(value = Zubehoer.class, name = AbstractArtikel.ZUBEHOER) 
        })
      
		public abstract class AbstractArtikel implements Serializable {
	
	    private static final long serialVersionUID = -97562639100824340L;

		public static final String ERSATZTEILE = "E";
        public static final String FAHRRAD = "F";
        public static final String ZUBEHOER = "Z";
        
        private Long artikelnummer;
        private String name;
        private Double stueckpreis;
        private Integer bestand;
        private Unterkategorie unterkategorie;
        private URI bestellungenUri;
                
        public Long getArtikelnummer() {
                return artikelnummer;
        }
        public void setArtikelnummer(Long artikelnummer) {
                this.artikelnummer = artikelnummer;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public Double getStueckpreis() {
                return stueckpreis;
        }
        public void setStueckpreis(Double stueckpreis) {
                this.stueckpreis = stueckpreis;
        }
        public Integer getBestand() {
                return bestand;
        }
        public void setBestand(Integer bestand) {
                this.bestand = bestand;
        }
        public URI getBestellungenUri() {
                return bestellungenUri;
        }
        public void setBestellungenUri(URI bestellungenUri) {
                this.bestellungenUri = bestellungenUri;
        }
        
        public Unterkategorie getUnterkategorie() {
                return unterkategorie;
        }
        public void setUnterkategorie(Unterkategorie unterkategorie) {
                this.unterkategorie = unterkategorie;
        }
       
        
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((artikelnummer == null) ? 0 : artikelnummer.hashCode());
			result = prime * result
					+ ((bestand == null) ? 0 : bestand.hashCode());
			result = prime
					* result
					+ ((bestellungenUri == null) ? 0 : bestellungenUri
							.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result
					+ ((stueckpreis == null) ? 0 : stueckpreis.hashCode());
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
			AbstractArtikel other = (AbstractArtikel) obj;
			if (artikelnummer == null) {
				if (other.artikelnummer != null)
					return false;
			} else if (!artikelnummer.equals(other.artikelnummer))
				return false;
			if (bestand == null) {
				if (other.bestand != null)
					return false;
			} else if (!bestand.equals(other.bestand))
				return false;
			if (bestellungenUri == null) {
				if (other.bestellungenUri != null)
					return false;
			} else if (!bestellungenUri.equals(other.bestellungenUri))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (stueckpreis == null) {
				if (other.stueckpreis != null)
					return false;
			} else if (!stueckpreis.equals(other.stueckpreis))
				return false;
			return true;
		}
        
        @Override
        public String toString() {
                return "AbstractArtikel [Artikelnummer=" + artikelnummer + ", name=" + name + ", Unterkategorie:" + unterkategorie.toString() + ", bestellungenUri=" + bestellungenUri + "]";
        }

}
