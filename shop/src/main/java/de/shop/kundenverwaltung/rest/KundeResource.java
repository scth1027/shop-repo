package de.shop.kundenverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



@Path("/kunden")
@Produces({APPLICATION_JSON, APPLICATION_XML, TEXT_PLAIN} )
@Consumes
public class KundeResource {
	
}