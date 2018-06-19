package br.com.backend.requisitos.service;

import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demoiselle.jee.crud.AbstractREST;

import br.com.backend.requisitos.bc.RequisitoBC;
import br.com.backend.requisitos.entity.Requisito;
import io.swagger.util.Json;

@Path("projeto/{idProjeto}/requisito")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequisitoREST extends AbstractREST<Requisito, Integer> {
	private static final Logger LOG = getLogger(RequisitoREST.class.getName());
		
	@POST
    public Response create(@PathParam("idProjeto") final Integer idProjeto, Requisito r) {
        try {
        	((RequisitoBC)bc).create(idProjeto, r);
        	return Response.ok(r.getId()).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	return Response.serverError().entity(Json.pretty(e.getMessage())).build();
        }
    }
	
	@GET
	// @CacheControl(value = "max-age=3600, must-revalidate, public") // caso queira cache em algum serviço
	@Path("/list")
    public Response list(
    		@PathParam("idProjeto") final Integer idProjeto
    ) {
        try {
        	return Response.ok(((RequisitoBC)bc).list(idProjeto)).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	LOG.severe("Erro não tratado");
        	return Response.serverError().build();
        }
    }
	
	@GET
	@Path("/{idRequisito}")
	public Response buscar(
			@PathParam("idProjeto") final Integer idProjeto,
			@PathParam("idRequisito") final Integer idRequisito) {
		try {
			return Response.ok(((RequisitoBC) bc).find(idProjeto, idRequisito)).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/{idRequisito}")
	@Transactional
	public Response alterar(Requisito r, @PathParam("idRequisito") final Integer idRequisito) {
		try {
			bc.mergeHalf(idRequisito, r);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/{idRequisito}")
	@Transactional
	public Response excluir(@PathParam("idRequisito") final Integer idRequisito) {
		try {
			bc.remove(idRequisito);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
