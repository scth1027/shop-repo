package de.shop.kundenverwaltung.service;

import static de.shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.ImmutableMap;

import de.shop.kundenverwaltung.domain.AbstractKunde;
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
	
	//@Inject
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
	public List<AbstractKunde> findKundenByNachname(String nachname, FetchType fetch) {
		final TypedQuery<AbstractKunde> query = em.createNamedQuery(AbstractKunde.FIND_KUNDEN_BY_NACHNAME,
                                                                    AbstractKunde.class)
                                                  .setParameter(AbstractKunde.PARAM_KUNDE_NACHNAME, nachname);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
				
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			default:
				break;
		}
		
		return query.getResultList();
	}
	
	@NotNull(message = "{kunde.notFound.email}")
	public AbstractKunde findKundeByEmail(String email) {
		try {
			return em.createNamedQuery(AbstractKunde.FIND_KUNDE_BY_EMAIL, AbstractKunde.class)
					 .setParameter(AbstractKunde.PARAM_KUNDE_EMAIL, email)
					 .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	
	public List<AbstractKunde> findAllKunden(FetchType fetch, OrderType order) {
		final TypedQuery<AbstractKunde> query = OrderType.ID.equals(order)
		                                        ? em.createNamedQuery(AbstractKunde.FIND_KUNDEN_ORDER_BY_ID,
		                                        		              AbstractKunde.class)
		                                        : em.createNamedQuery(AbstractKunde.FIND_KUNDEN, AbstractKunde.class);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(AbstractKunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;

			default:
				break;
		}

		return query.getResultList();
	}
	
	public <T extends AbstractKunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}

		final AbstractKunde tmp = findKundeByEmail(kunde.getEmail());   // Kein Aufruf als Business-Methode
		if (tmp != null) {
			throw new EmailExistsException(kunde.getEmail());
		}
		
		em.persist(kunde);
		return kunde;		
	}
	
	
	
	public <T extends AbstractKunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}
		
		
		em.detach(kunde);
		
		final AbstractKunde tmp = findKundeByEmail(kunde.getEmail()); 
		if (tmp != null) {
			em.detach(tmp);
			if (tmp.getId().longValue() != kunde.getId().longValue()) {
				throw new EmailExistsException(kunde.getEmail());
			}
		}

		em.merge(kunde);
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
		
		if (!kunde.getBestellungen().isEmpty()) {
			throw new KundeDeleteBestellungException(kunde);
		}

		em.remove(kunde);
	}

	
}
	
	


