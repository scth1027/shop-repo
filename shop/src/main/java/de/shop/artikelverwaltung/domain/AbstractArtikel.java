package de.shop.artikelverwaltung.domain;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jboss.logging.Logger;




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
	    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

		public static final String ERSATZTEILE = "E";
        public static final String FAHRRAD = "F";
        public static final String ZUBEHOER = "Z";
        
        private static final int NAME_LENGTH_MAX = 32;
        private static final String PREFIX = "AbstractArtikel.";
        public static final String FIND_AVAILABLE = PREFIX + "findVerfuegbareArtikel";
        public static final String FIND_ARTIKEL_BY_NAME = PREFIX + "findArtikelByName";
        public static final String FIND_ARTIKEL_MAX_PREIS = PREFIX + "findArtikelByMaxPreis";
        public static final String PARAM_NAME = "name";
        public static final String PARAM_PREIS = "preis";
        
        @Id
        @GeneratedValue
        @Column(nullable = false, updatable = false)
        private Long id = null;
        
        @Column(length = NAME_LENGTH_MAX, nullable = false)
        @NotNull(message = "{artikel.name.notNull}")
        @Size(max = NAME_LENGTH_MAX, message = "{artikel.name.length}")
        private String name = "";
        
        @Column(precision = 8, scale = 2)
        private BigDecimal stueckpreis;
        
        @Min(0)
        @Max(99999)
        private Integer bestand;
        
        private boolean ausgesondert;
        
        @Basic(optional = false)
        @Temporal(TIMESTAMP)
        @XmlTransient
        private Date create;

        @Basic(optional = false)
        @Temporal(TIMESTAMP)
        @XmlTransient
        private Date update;
        
        @PrePersist
        protected void prePersist() {
                create = new Date();
                update = new Date();
        }
        
        @PostPersist
        protected void postPersist() {
                LOGGER.debugf("Neuer Artikel mit ID=%d", id);
        }
        
        @PreUpdate
        protected void preUpdate() {
                update = new Date();
        }
        
        public void setValues(AbstractArtikel a) {
                name = a.name;
                stueckpreis = a.stueckpreis;
                bestand = a.bestand;
        }
                
        public Long getId() {
                return id;
        }
        public void setId(Long Id) {
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
        public Date getcreate() {
        		return create == null ? null : (Date) create.clone();
        }

        public void setcreate(Date create) {
            	this.create = create == null ? null : (Date) create.clone();
        }

        public Date getupdate() {
            	return update == null ? null : (Date) update.clone();
        }

        public void setupdate(Date update) {
            	this.update = update == null ? null : (Date) update.clone();
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
                       + ", stueckpreis=" + stueckpreis + ", ausgesondert=" + ausgesondert
                       + ", create=" + create
                           + ", update=" + update + "]";
        }

}
