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

import br.com.backend.requisitos.bc.ProjetoBC;
import br.com.backend.requisitos.entity.Projeto;
import io.swagger.util.Json;

@Path("projeto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjetoREST extends AbstractREST<Projeto, Integer> {
	private static final Logger LOG = getLogger(ProjetoREST.class.getName());
	
	@POST
    public Response create(Projeto p) {
        try {
        	((ProjetoBC)bc).create(p);
        	return Response.ok(p.getId()).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	return Response.serverError().entity(Json.pretty(e.getMessage())).build();
        }
    }
	
	@GET
	// @CacheControl(value = "max-age=3600, must-revalidate, public") // caso queira cache em algum serviço
	@Path("/list")
    public Response list() {
        try { 
        	return Response.ok(((ProjetoBC)bc).list()).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	LOG.severe("Erro não tratado");
        	return Response.serverError().build();
        }
    }

	@GET
	@Path("/{idProjeto}")
	public Response buscar(@PathParam("idProjeto") final Integer idProjeto) {
		try {
			return Response.ok(((ProjetoBC)bc).listDetailed(idProjeto)).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(Json.pretty(e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("/{idProjeto}")
	@Transactional
	public Response alterar(Projeto p, @PathParam("idProjeto") final Integer idProjeto) {
		try {
			bc.mergeHalf(idProjeto, p);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/{idProjeto}")
	@Transactional
	public Response excluir(@PathParam("idProjeto") final Integer idProjeto) {
		try {
			bc.remove(idProjeto);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}







