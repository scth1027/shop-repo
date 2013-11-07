package de.shop.artikelverwaltung.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteile;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Farbe;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.artikelverwaltung.domain.Unterkategorie;


/**
 * Emulation des Anwendungskerns
 */


public final class Mock {
	private static final int MAX_ARTIKELNUMMER = 10000;
	private static final int MAX_ARTIKEL = 8;

	public static AbstractArtikel findArtikelByID(Long artikelnummer) {
		if (artikelnummer > MAX_ARTIKELNUMMER) {
			return null;
		}
		
		AbstractArtikel artikel = null;
		if(artikelnummer < 1000)
			artikel = new Fahrrad();
		else if(artikelnummer > 1000 && artikelnummer < 5000)
			artikel = new Ersatzteile();
		else
			artikel = new Zubehoer();
		
		artikel.setArtikelnummer(artikelnummer);
		artikel.setName("Artikel" + artikelnummer);
		artikel.setStueckpreis(0.00);
		artikel.setBestand(0);
		
		final Unterkategorie unterkategorie = new Unterkategorie();
		unterkategorie.setKlassenId((long)12345);        
		unterkategorie.setName("Testunterkategorie");
		unterkategorie.setBeschreibung("Testbeschreibung");
		//unterkategorie.setArtikel(artikel);
		artikel.setUnterkategorie(unterkategorie);
		
		if (artikel.getClass().equals(Ersatzteile.class)) {
			final Ersatzteile ersatzteile = (Ersatzteile) artikel;
			ersatzteile.setArtikeldetails("Artikeldetails");
		}
		else if (artikel.getClass().equals(Fahrrad.class)) {
			final Fahrrad fahrrad = (Fahrrad) artikel;
			final Set<Farbe> farbe = new HashSet<>();
			farbe.add(Farbe.SCHWARZ);
			farbe.add(Farbe.ROT);
			fahrrad.setFarbe(farbe);
		}
		else if (artikel.getClass().equals(Zubehoer.class)) {
			final Zubehoer zubehoerx1 = (Zubehoer) artikel;
			zubehoerx1.setArtikeldetails_z("Artikeldetails");
//			zubehoerx1.setBremsverstaerker("Bremsverstaerker");
//			zubehoerx1.setTacho("Tacho");
//			zubehoerx1.setLicht("Licht");
//			zubehoerx1.setPulsmesser("Pulsmesser");
//			zubehoerx1.setFlaschenhalter("Flaschenhalter");
		}
		
		return artikel;
	}

	public static List<AbstractArtikel> findAllArtikel() {
		final int anzahl = MAX_ARTIKEL;
		final List<AbstractArtikel> alleArtikel = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractArtikel artikel = findArtikelByID(Long.valueOf(i));
			alleArtikel.add(artikel);			
		}
		return alleArtikel;
	}

	public static List<AbstractArtikel> findArtikelByName(String name) {
		final int anzahl = name.length();
		final List<AbstractArtikel> alleArtikel = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractArtikel artikel = findArtikelByID(Long.valueOf(i));
			artikel.setName(name);
			alleArtikel.add(artikel);			
		}
		return alleArtikel;
	}
	

	public static AbstractArtikel createArtikel(AbstractArtikel artikel) {
		// Neue IDs fuer Artikel und zugehoerigem Preis + Bestand
		// Ein neuer Artikel gehört auch zu keinen Bestellungen
		final String name = artikel.getName();
		artikel.setArtikelnummer(Long.valueOf(name.length()));
		final Unterkategorie unterkategoriex1 = artikel.getUnterkategorie();
		unterkategoriex1.setKlassenId((Long.valueOf(name.length())) + 1);
		//unterkategoriex1.setArtikel(artikel);
		
		System.out.println("Neuer Artikel: " + artikel);
		return artikel;
	}

	public static void updateArtikel(AbstractArtikel artikel) {
		System.out.println("Aktualisierter Artikel: " + artikel);
	}

	public static void deleteArtikel(Long artikelnummer) {
		System.out.println("Artikel mit ID=" + artikelnummer + " geloescht");
	}

	private Mock() {}
}