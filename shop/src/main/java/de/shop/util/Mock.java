package de.shop.util;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Firmenkunde;
import de.shop.kundenverwaltung.domain.HobbyType;
import de.shop.kundenverwaltung.domain.Privatkunde;
import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteile;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;

/**
 * Emulation des Anwendungskerns
 */
public final class Mock {
	 private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final int MAX_BESTELLUNGEN = 4;
	private static final int MAX_ARTIKEL = 500;
	private static final int fahval = 200;
    private static final int ersval = 300;
    private static final int zubval = 400;

	public static AbstractKunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final AbstractKunde kunde = id % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(id);
		kunde.setNachname("Nachname" + id);
		kunde.setEmail("" + id + "@hska.de");
		
		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);        // andere ID fuer die Adresse
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
		if (kunde.getClass().equals(Privatkunde.class)) {
			final Privatkunde privatkunde = (Privatkunde) kunde;
			final Set<HobbyType> hobbies = new HashSet<>();
			hobbies.add(HobbyType.FUSSBALL);
			hobbies.add(HobbyType.REITEN);
			privatkunde.setHobbies(hobbies);
		}
		
		return kunde;
	}

	public static List<AbstractKunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);			
		}
		return kunden;
	}

	public static List<AbstractKunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	

	public static List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getId().intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);			
		}
		kunde.setBestellungen(bestellungen);
		
		return bestellungen;
	}

	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final AbstractKunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setStatus(false);
		bestellung.setKunde(kunde);
		//bestellung.setBestelldatum(01:01:99);
		bestellung.setGesamtpreis(new BigDecimal("112.43"));
		
		

		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		bestellung.setLieferadresse(adresse);


		
		return bestellung;
	}
	public static <T extends AbstractKunde> T createKunde(T kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setId(Long.valueOf(nachname.length()));
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((Long.valueOf(nachname.length())) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);
		
		System.out.println("Neuer Kunde: " + kunde);
		return kunde;
	}

	public static void updateKunde(AbstractKunde kunde) {
		System.out.println("Aktualisierter Kunde: " + kunde);
	}

	public static void deleteKunde(Long kundeId) {
		System.out.println("Kunde mit ID=" + kundeId + " geloescht");
	}

	public static Bestellung createBestellung(Bestellung bestellung) {
		final AbstractKunde kunde = bestellung.getKunde();
        bestellung.setId(Long.valueOf("2"));
        final BigDecimal gesamtpreis = bestellung.getGesamtpreis();
        bestellung.setGesamtpreis(gesamtpreis);
       // gesamtpreis.setScale(15);
        

		System.out.println("Neue Bestellung: " + bestellung);
		return bestellung;
	}
	
	 public static void updateBestellung(Bestellung bestellung) {
         System.out.println("Aktualisierter Bestellung: " + bestellung);
 }


 public static void deleteBestellung(Long bestellungId) {
         System.out.println("Bestellung mit ID=" + bestellungId + " geloescht");
 }

 public static AbstractArtikel findArtikelById(Long id) {
     if (id > MAX_ARTIKEL) {
             return null;
     }
     
     final AbstractArtikel artikel;
     if (fahval < id && id < ersval)
             artikel = new Fahrrad();
     else if (ersval < id && id < zubval)
             artikel = new Ersatzteile();
     else
             artikel = new Zubehoer();
     
     artikel.setId(id);
     artikel.setName("Name: " + id);
     
     return artikel;        
}

public static List<AbstractArtikel> findAllArtikel() {
     final int anzahl = MAX_ARTIKEL;
     final List<AbstractArtikel> artikels = new ArrayList<>(anzahl);
     for (int i = 1; i <= anzahl; i++) {
             final AbstractArtikel artikel = findArtikelById(Long.valueOf(i));
             artikels.add(artikel);                        
     }
     return artikels;
}

public static <T extends AbstractArtikel> T createArtikel(T artikel) {
     // Neue IDs fuer Artikel
     final String name = artikel.getName();
     artikel.setId(Long.valueOf(name.length()));
     artikel.setStueckpreis(null);
     
     LOGGER.infof("Neuer Artikel: %s", artikel);
     return artikel;
}

public static void updateArtikel(AbstractArtikel artikel) {
     LOGGER.infof("Aktualisierter Artikel: %s", artikel);
}

public static void deleteArtikel(AbstractArtikel artikel) {
     LOGGER.infof("Geloeschter Artikel: %s", artikel);
}
	
	private Mock() { /**/ }
}
