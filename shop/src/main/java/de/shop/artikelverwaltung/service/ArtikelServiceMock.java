package de.shop.artikelverwaltung.service;

import javax.enterprise.context.Dependent;
import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteile;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.util.cdi.MockService;
import de.shop.util.interceptor.Log;
import java.io.Serializable;

@MockService
@Dependent
@Log
public class ArtikelServiceMock extends ArtikelService {
	private static final long serialVersionUID = 6728696474015590029L;
	private final int FAHVAL = 200;
	private final int ERSVAL = 300;
	private final int ZUBVAL = 400;
	
	@Override
	public AbstractArtikel findArtikelById(Long id) {
		if (id == null) {
			return null;
		}
		final AbstractArtikel artikel;
		if (FAHVAL < id && id < ERSVAL)
			artikel = new Fahrrad();
		else if (ERSVAL < id && id < ZUBVAL)
			artikel = new Ersatzteile();
		else
			artikel = new Zubehoer();
		artikel.setId(id);
		artikel.setName("Name: " + id);
		return artikel;
	}
}