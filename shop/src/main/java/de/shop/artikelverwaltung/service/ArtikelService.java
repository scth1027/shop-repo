package de.shop.artikelverwaltung.service;

import java.io.Serializable;

import javax.persistence.criteria.Path;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.criteria.Predicate;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.util.interceptor.Log;

import com.google.common.base.Strings;

@Dependent
@Log
public class ArtikelService implements Serializable {
        private static final long serialVersionUID = 2929027248430844352L;
        
        @Inject
        private transient EntityManager em;
        
        /**
         * Suche nach verfuegbaren Artikeln.
         * @return Liste der verfuegbaren Artikel.
         */
        public List<Artikel> findVerfuegbareArtikel() {
                return em.createNamedQuery(Artikel.FIND_VERFUEGBARE_ARTIKEL, Artikel.class)
                                 .getResultList();
        }

        
        /**
         * Suche den Artikel zu gegebener ID.
         * @param id ID des gesuchten Artikels.
         * @return Der gefundene Artikel, null sonst.
         * @exception ConstraintViolationException zu @NotNull, falls kein Artikel gefunden wurde
         */
        @NotNull(message = "{artikel.notFound.id}")
        public Artikel findArtikelById(Long id) {

                return em.find(Artikel.class, id);
        }
        
        /**
         * Suche die Artikel zu gegebenen IDs.
         * @param ids Liste der IDs
         * @return Liste der gefundenen Artikel
         * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
         */
        @Size(min = 1, message = "{artikel.notFound.id}")
        public List<Artikel> findArtikelByIds(List<Long> ids) {
                if (ids == null || ids.isEmpty()) {
                        return Collections.emptyList();
                }
                
                /*
                 * SELECT a
                 * FROM Artikel a
                 * WHERE a.id = ? OR a.id = ? OR ...
                 */
                final CriteriaBuilder builder = em.getCriteriaBuilder();
                final CriteriaQuery<Artikel> criteriaQuery = builder.createQuery(Artikel.class);
                final Root<Artikel> a = criteriaQuery.from(Artikel.class);

                final Path<Long> idPath = a.get("id");
                //final Path<String> idPath = a.get(Artikel_.id); // Metamodel-Klassen funktionieren nicht mit Eclipse
                
                Predicate pred = null;
                if (ids.size() == 1) {
                        // Genau 1 id: kein OR notwendig
                        pred = builder.equal(idPath, ids.get(0));
                }
                else {
                        // Mind. 2x id, durch OR verknuepft
                        final Predicate[] equals = new Predicate[ids.size()];
                        int i = 0;
                        for (Long id : ids) {
                                equals[i++] = builder.equal(idPath, id);
                        }
                        
                        pred = builder.or(equals);
                }
                
                criteriaQuery.where(pred);
                
                return em.createQuery(criteriaQuery)
                 .getResultList();
        }

        
        /**
         * Suche die Artikel mit gleicher Bezeichnung
         * @param bezeichnung Gemeinsame Bezeichnung der gesuchten Artikel
         * @return Liste der gefundenen Artikel
         * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
         */
        @Size(min = 1, message = "{artikel.notFound.bezeichnung}")
        public List<Artikel> findArtikelByBezeichnung(String bezeichnung) {
                if (Strings.isNullOrEmpty(bezeichnung)) {
                        return findVerfuegbareArtikel();
                }
                
                return em.createNamedQuery(Artikel.FIND_ARTIKEL_BY_BEZ, Artikel.class)
                                 .setParameter(Artikel.PARAM_BEZEICHNUNG, "%" + bezeichnung + "%")
                                 .getResultList();
        }
        
        /**
         * Erstellt Artikel
         * @return Erzeugter Artikel
         */
        public <T extends Artikel> T createArtikel(T artikel) {
                if (artikel == null) {
                        return null;
                }
                
                // Pruefung, ob ein solcher Artikel schon existiert
        final List<Artikel> tmp = findArtikelByBezeichnung(artikel.getBezeichnung());
        for (Artikel a : tmp) {
                if (a.getBezeichnung() == artikel.getBezeichnung())
                        throw new BezeichnungExistsException(artikel.getBezeichnung());
        }
                em.persist(artikel);
                return artikel;
        }

        public <T extends Artikel> T updateArtikel(T artikel) {
                if (artikel == null) {
                        return null;
                }
                
                final Artikel tmp = findArtikelById(artikel.getId());
                artikel.setErzeugt(tmp.getErzeugt());
                em.merge(artikel);
                return artikel;
        }
}