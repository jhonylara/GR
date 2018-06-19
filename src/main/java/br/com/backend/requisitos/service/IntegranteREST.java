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

import br.com.backend.requisitos.bc.IntegranteBC;
import br.com.backend.requisitos.entity.Integrante;
import io.swagger.util.Json;

@Path("projeto/{idProjeto}/integrante")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IntegranteREST extends AbstractREST<Integrante, Integer> {
	private static final Logger LOG = getLogger(IntegranteREST.class.getName());
	
	@POST
    public Response create(@PathParam("idProjeto") final Integer idProjeto, Integrante i) {
        try {
        	((IntegranteBC)bc).create(idProjeto, i);
        	return Response.ok().build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	return Response.serverError().entity(Json.pretty(e.getMessage())).build();
        }
    }
	
	@GET
	// @CacheControl(value = "max-age=3600, must-revalidate, public") // caso queira cache em algum serviço
	@Path("/list")
    public Response list(@PathParam("idProjeto") final Integer idProjeto) {
        try {
        	return Response.ok(((IntegranteBC) bc).list(idProjeto)).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	LOG.severe("Erro não tratado");
        	return Response.serverError().build();
        }
    }
	
	@GET
	@Path("/{idIntegrante}")
	public Response buscar(
		@PathParam("idProjeto") final Integer idProjeto,
		@PathParam("idIntegrante") final Integer idIntegrante
	) {
		try {
			return Response.ok(((IntegranteBC) bc).findById(idProjeto, idIntegrante)).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/{idIntegrante}")
	@Transactional
	public Response alterar(Integrante i, @PathParam("idIntegrante") final Integer idIntegrante) {
		try {
			bc.mergeHalf(idIntegrante, i);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/{idIntegrante}")
	@Transactional
	public Response excluir(@PathParam("idIntegrante") final Integer idIntegrante) {
		try {
			bc.remove(idIntegrante);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
