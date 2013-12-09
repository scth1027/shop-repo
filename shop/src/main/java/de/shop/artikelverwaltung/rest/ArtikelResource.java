package de.shop.artikelverwaltung.rest;


import static de.shop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.lang.invoke.MethodHandles;
import java.net.URI;
//import java.util.List;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.shop.artikelverwaltung.domain.AbstractArtikel;
import de.shop.artikelverwaltung.service.ArtikelService;
import de.shop.util.interceptor.Log;
import de.shop.util.rest.UriHelper;
import de.shop.util.rest.NotFoundException;


@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.75" })
@Consumes
@RequestScoped
@Log
public class ArtikelResource {
        
        private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
        
        @Context
        private UriInfo uriInfo;
        
        @Inject
        private ArtikelService as;
        
        @Inject
        private UriHelper uriHelper;
        
        @PostConstruct
        private void postConstruct() {
                LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
        }
        
        @PreDestroy
        private void preDestroy() {
                LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
        }
        
        @GET
        @Path("{id:[1-9][0-9]*}")
        public Response findArtikelById(@PathParam("id") Long id) {
                final AbstractArtikel artikel = as.findArtikelById(id);
                if (artikel == null) {
        			throw new NotFoundException("Artikel mit der " + id + "konnte nicht gefunden werden");
        		}
                return Response.ok(artikel)
                       .links(getTransitionalLinks(artikel, uriInfo))
                       .build();
        }
        
        private Link[] getTransitionalLinks(AbstractArtikel artikel, UriInfo uriInfo) {
                final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
                              .rel(SELF_LINK)
                              .build();

                return new Link[] {self };
        }
        
        public URI getUriArtikel(AbstractArtikel artikel, UriInfo uriInfo) {
                return uriHelper.getUri(ArtikelResource.class, "findArtikelById", artikel.getId(), uriInfo);
        }

        @POST
        @Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
        @Produces
        public Response createArtikel(@Valid AbstractArtikel artikel) {
                artikel = as.createArtikel(artikel);
                return Response.created(getUriArtikel(artikel, uriInfo))
                                   .build();
        }
        
        
        @PUT
        @Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
        @Produces
        public void updateArtikel(@Valid AbstractArtikel artikel) {
                as.updateArtikel(artikel);
        }
        
        @DELETE
        @Path("{id:[1-9][0-9]*}")
        @Produces
        public void deleteArtikel(@PathParam("id") Long id) {
                as.deleteArtikel(id);
        }
        
}
