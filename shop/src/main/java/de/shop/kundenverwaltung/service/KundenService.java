package de.shop.kundenverwaltung.service;

import static de.shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.ImmutableMap;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.Mock;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class KundenService implements Serializable {

	private static final long serialVersionUID = 5326204988915561298L;
	
	public enum FetchType {
		NUR_KUNDE,
		MIT_BESTELLUNGEN,
	}
	
	public enum OrderType {
		KEINE,
		ID
	}
	
	@Inject
	private transient EntityManager em;

	@NotNull(message = "{kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
		
		AbstractKunde kunde;
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		switch (fetch) {
			case NUR_KUNDE:
				kunde = em.find(AbstractKunde.class, id);
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(AbstractKunde.class, id, props);
				break;
				
			default:
				kunde = em.find(AbstractKunde.class, id);
				break;
		}
		
		return kunde;
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
	
	public void deleteKunde(AbstractKunde kunde) {
		if (kunde == null) {
			return;
		}
		
		kunde = findKundeById(kunde.getId(), FetchType.MIT_BESTELLUNGEN); 
		if (kunde == null) {
			return;
		}
		
		// Gibt es Bestellungen?
		if (!kunde.getBestellungen().isEmpty()) {
			//throw new KundeDeleteBestellungException(kunde);
		}

		em.remove(kunde);
	}

	
}
	
	


