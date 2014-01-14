package de.shop.artikelverwaltung.domain;

import static de.shop.util.Constants.KEINE_ID;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.jboss.logging.Logger;

import de.shop.util.persistence.AbstractAuditable;

@Entity
@Table(indexes = @Index(columnList = "bezeichnung"))
@NamedQueries({
        @NamedQuery(name = Artikel.FIND_VERFUEGBARE_ARTIKEL,
                    query = "SELECT a"
                     + " FROM Artikel a"
                                                + " WHERE a.ausgesondert = FALSE"
                        + " ORDER BY a.id ASC"),
        @NamedQuery(name = Artikel.FIND_ARTIKEL_BY_BEZ,
                    query = "SELECT a"
                        + " FROM Artikel a"
                                                + " WHERE a.bezeichnung LIKE :" + Artikel.PARAM_BEZEICHNUNG
                                                + " AND a.ausgesondert = FALSE"
                                  + " ORDER BY a.id ASC"),
           @NamedQuery(name = Artikel.FIND_ARTIKEL_MAX_PREIS,
                    query = "SELECT a"
                        + " FROM Artikel a"
                                                + " WHERE a.preis < :" + Artikel.PARAM_PREIS
                                  + " ORDER BY a.id ASC") })
@Cacheable
@XmlRootElement
public class Artikel extends AbstractAuditable implements Serializable {
        private static final long serialVersionUID = 1472129607838538329L;
        private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
        
        private static final String PREFIX = "Artikel.";
        public static final String FIND_VERFUEGBARE_ARTIKEL = PREFIX + "findVerfuegbareArtikel";
        public static final String FIND_ARTIKEL_BY_BEZ = PREFIX + "findArtikelByBez";
        public static final String FIND_ARTIKEL_MAX_PREIS = PREFIX + "findArtikelByMaxPreis";
        
        public static final String PARAM_BEZEICHNUNG = "bezeichnung";
        public static final String PARAM_PREIS = "preis";
        
        private static final int BEZEICHNUNG_LENGTH_MIN = 2;
        private static final int BEZEICHNUNG_LENGTH_MAX = 32;
         public static final String BEZEICHNUNG_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF0-9]+"
             + "(-[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF0-9]+)?";
        
        @Id
        @GeneratedValue
        @Basic(optional = false)
        private Long id = KEINE_ID;
        
        
        @Digits(integer = 10, fraction = 2, message = "{artikel.preis.digits}")
        private BigDecimal preis;
        
        @NotNull(message = "{artikel.bezeichnung.notNull}")
        @Size(min = BEZEICHNUNG_LENGTH_MIN, max = BEZEICHNUNG_LENGTH_MAX, message = "{artikel.bezeichnung.length}")
        private String bezeichnung = "";
        
        @Basic(optional = false)
        private boolean ausgesondert;
        
        @PostPersist
        private void postPersist() {
                LOGGER.debugf("Neuer Artikel mit ID=%d", id);
        }
        
        public boolean isAusgesondert() {
                return ausgesondert;
        }
        public void setAusgesondert(boolean ausgesondert) {
                this.ausgesondert = ausgesondert;
        }
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
        public String getBezeichnung() {
                return bezeichnung;
        }
        public void setBezeichnung(String bezeichnung) {
                this.bezeichnung = bezeichnung;
        }
        public BigDecimal getPreis() {
                return preis;
        }
        public void setPreis(BigDecimal preis) {
                this.preis = preis;
        }
        
        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + (ausgesondert ? 1231 : 1237);
                result = prime * result
                                + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
                result = prime * result + ((preis == null) ? 0 : preis.hashCode());
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
                if (ausgesondert != other.ausgesondert)
                        return false;
                if (bezeichnung == null) {
                        if (other.bezeichnung != null) {
                                return false;
                        }
                }
                else if (!bezeichnung.equals(other.bezeichnung)) {
                        return false;
                }
                if (preis == null) {
                        if (other.preis != null) {
                                return false;
                        }
                }
                else if (!preis.equals(other.preis)) {
                        return false;
                }
                return true;
        }

        @Override
        public String toString() {
                return "Artikel [id=" + id + ", bezeichnung=" + bezeichnung
                 + ", preis=" + preis + ", ausgesondert=" + ausgesondert
                 + ", " + super.toString() + "]";
        }
}