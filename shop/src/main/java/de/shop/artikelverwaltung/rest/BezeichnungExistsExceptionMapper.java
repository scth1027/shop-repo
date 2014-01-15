package de.shop.artikelverwaltung.rest;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import de.shop.artikelverwaltung.service.BezeichnungExistsException;
import de.shop.util.interceptor.Log;
import de.shop.util.rest.Messages;

@Provider
@Log
public class BezeichnungExistsExceptionMapper implements ExceptionMapper<BezeichnungExistsException> {
        @Context
        private HttpHeaders headers;

        @Inject
        private Messages messages;

        @Override
        public Response toResponse(BezeichnungExistsException e) {
                final String msg = messages.getMessage(headers, e.getMessageKey(), e.getBezeichnung());
                return Response.status(BAD_REQUEST).type(TEXT_PLAIN).entity(msg).build();
        }
}
