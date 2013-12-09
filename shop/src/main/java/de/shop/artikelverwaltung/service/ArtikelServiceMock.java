package de.shop.artikelverwaltung.service;

import javax.enterprise.context.Dependent;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.domain.Ersatzteile;
import de.shop.artikelverwaltung.domain.Fahrrad;
import de.shop.artikelverwaltung.domain.Zubehoer;
import de.shop.util.cdi.MockService;
import de.shop.util.interceptor.Log;


@MockService
@Dependent
@Log
public class ArtikelServiceMock extends ArtikelService {
  
	private static final long serialVersionUID = 6933383106825222035L;
		private final int fahval = 200;
        private final int ersval = 300;
        private final int sichval = 400;

      
        @Override
        public AbstractArtikel findArtikelById(Long id) {
                if (id == null) {
                        return null;
                }
                
                final AbstractArtikel artikel;
                if (fahval < id && id < ersval)
                        artikel = new Fahrrad();
                else if (ersval < id && id < sichval)
                        artikel = new Ersatzteile();
                else
                        artikel = new Zubehoer();
                
                artikel.setId(id);
                artikel.setName("Name: " + id);
                
                return artikel;        
        }
}