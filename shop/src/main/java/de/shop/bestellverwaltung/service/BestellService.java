package de.shop.bestellverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import de.shop.util.Mock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Logger;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class BestellService implements Serializable {

	private static final long serialVersionUID = 6910977506144760877L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	 @PostConstruct
     private void postConstruct() {
             LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
     }
     
     @PreDestroy
     private void preDestroy() {
             LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
     }
     
     @NotNull(message = "{bestellung.notFound.id}")
     public Bestellung findBestellungById(Long id) {
             if (id == null) {
                     return null;
             }
             // TODO Datenbanzugriffsschicht statt Mock
             return Mock.findBestellungById(id);      
     }
     
     public List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
         // TODO Datenbanzugriffsschicht statt Mock
         return Mock.findBestellungenByKunde(kunde);
     }
     
     public <T extends Bestellung> Bestellung createBestellung(Bestellung bestellung) {
         if (bestellung == null) {
                 return bestellung;
         }
         // TODO Datenbanzugriffsschicht statt Mock
         bestellung = Mock.createBestellung(bestellung);

         return bestellung;
 }
     
     public <T extends Bestellung> T updateBestellung(T bestellung) {
         if (bestellung == null) {
                 return null;
         }

         // TODO Datenbanzugriffsschicht statt Mock
         Mock.updateBestellung(bestellung);
         
         return bestellung;
 }
  
     public void deleteBestellung(Long bestellId) {
         final Bestellung bestellung = findBestellungById(bestellId);  // Kein Aufruf als Business-Methode
         if (bestellung == null) {
                 return;
         }
         
         // TODO Datenbanzugriffsschicht statt Mock
         Mock.deleteBestellung(bestellId);
 }
	
	
	
	
	
	
}
