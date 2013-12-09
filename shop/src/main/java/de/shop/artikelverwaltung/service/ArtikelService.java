package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import java.util.List;

import de.shop.util.Mock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;

import javax.validation.constraints.NotNull;

import org.jboss.logging.Logger;



import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.util.interceptor.Log;



@Dependent
@Log
public class ArtikelService implements Serializable {
   
	private static final long serialVersionUID = 6910977506144760877L;
		private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
		 private final int fahval = 200;
	     private final int ersval = 300;
	     private final int zubval = 400;

        
        @PostConstruct
        private void postConstruct() {
                LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
        }
        
        @PreDestroy
        private void preDestroy() {
                LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
        }
        

        
        @NotNull(message = "{kunde.notFound.id}")
        public AbstractArtikel findArtikelById(Long id) {
                if (id == null) {
                        return null;
                }
                // TODO Datenbanzugriffsschicht statt Mock
                return Mock.findArtikelById(id);
        }
        
        public List<AbstractArtikel> findAllArtikel() {
                // TODO Datenbanzugriffsschicht statt Mock
                return Mock.findAllArtikel();
        }
        
        
        public <T extends AbstractArtikel> T createArtikel(T artikel) {
                if (artikel == null) {
                        return artikel;
                }
                // TODO Datenbanzugriffsschicht statt Mock
                artikel = Mock.createArtikel(artikel);

                return artikel;
        }
        
        public <T extends AbstractArtikel> T updateArtikel(T artikel) {
                if (artikel == null) {
                        return null;
                }

                // TODO Datenbanzugriffsschicht statt Mock
                Mock.updateArtikel(artikel);
                
                return artikel;
        }

        public void deleteArtikel(Long artikelId) {
                AbstractArtikel artikel = findArtikelById(artikelId);  // Kein Aufruf als Business-Methode
                if (artikel == null) {
                        return;
                }
                
                // TODO Datenbanzugriffsschicht statt Mock
                Mock.deleteArtikel(artikel);
        }
}