package de.shop.kundenverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.Mock;

@Dependent
public class KundenService implements Serializable {

	private static final long serialVersionUID = 5326204988915561298L;

	@NotNull(message = "{kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id) {
		if (id == null) {
			return null;
		}
		return Mock.findKundeById(id);
	}
	
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<AbstractKunde> findKundenByNachname(String nachname) {

		return Mock.findKundenByNachname(nachname);
	}
	
	public List<AbstractKunde> findAllKunden() {

		return Mock.findAllKunden();
	}
	
	public <T extends AbstractKunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}

		kunde = Mock.createKunde(kunde);

		return kunde;
	}
	
	public <T extends AbstractKunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}
		
		Mock.updateKunde(kunde);
		
		return kunde;
	}
	
	public void deleteKunde(Long kundeId) {
		final AbstractKunde kunde = findKundeById(kundeId); 
		if (kunde == null) {
			return;
		}
		
		Mock.deleteKunde(kundeId);
	}

	
}
	
	


