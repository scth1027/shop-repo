package de.shop.artikelverwaltung.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
        
        private static final String PREFIX = "AbstractArtikel.";
        public static final String FIND_AVAILABLE = PREFIX + "findVerfuegbareArtikel";
        public static final String FIND_ARTIKEL_BY_NAME = PREFIX + "findArtikelByName";
        public static final String FIND_ARTIKEL_MAX_PREIS = PREFIX + "findArtikelByMaxPreis";
        public static final String PARAM_NAME = "name";
        public static final String PARAM_PREIS = "preis";
        
        //@NotNull(message = "{artikel.id.NotNull}")
        private Long id;
      
        @NotNull(message = "{artikel.name.NotNull}")
        @Size(min = 2, max = 32, message = "{artikel.name.size}")
        @Pattern(regexp = "[A-ZÄÖÜ][a-zäöüß]+", message = "{artikel.name.pattern}")
        private String name = "";

        //@NotNull(message = "{artikel.stueckpreis.NotNull}")
        private BigDecimal stueckpreis;
        
        @Min(0)
        @Max(99999)
        private Integer bestand;
        
        //@NotNull(message = "{artikel.ausgesondert.NotNull}")
        private boolean ausgesondert;
                
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public BigDecimal getStueckpreis() {
                return stueckpreis;
        }
        public void setStueckpreis(BigDecimal stueckpreis) {
                this.stueckpreis = stueckpreis;
        }
        public Integer getBestand() {
                return bestand;
        }
        public void setBestand(Integer bestand) {
                this.bestand = bestand;
        }
        public boolean isAusgesondert() {
            	return ausgesondert;
        }

        public void setAusgesondert(boolean ausgesondert) {
            	this.ausgesondert = ausgesondert;
        }
        
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((bestand == null) ? 0 : bestand.hashCode());
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
                final AbstractArtikel other = (AbstractArtikel) obj;
                if (name == null) {
                        if (other.name != null) {
                                return false;
                        }
                }
                else if (!name.equals(other.name)) {
                        return false;
                }
                if (stueckpreis == null) {
                        if (other.stueckpreis != null) {
                                return false;
                        }
                }
                else if (!stueckpreis.equals(other.stueckpreis)) {
                        return false;
                }
                return true;
        }
        
        @Override
        public String toString() {
                return "Artikel [ID=" + id + ", name=" + name
                       + ", stueckpreis=" + stueckpreis + ", ausgesondert=" + ausgesondert + "]";
        }

}
