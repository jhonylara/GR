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

import br.com.backend.requisitos.bc.CasoDeUsoBC;
import br.com.backend.requisitos.entity.CasoDeUso;
import io.swagger.util.Json;

	@Path("projeto/{idProjeto}/CasoDeUso")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public class CasoDeUsoREST extends AbstractREST<CasoDeUso, Integer> {
		private static final Logger LOG = getLogger(ProjetoREST.class.getName());
		
		@POST
	    public Response create(@PathParam("idProjeto") final Integer idProjeto, CasoDeUso p	) {
	        try {
	        	((CasoDeUsoBC)bc).create(idProjeto, p);
	        	return Response.ok().entity(p).build();
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
	        	return Response.ok(((CasoDeUsoBC)bc).list(idProjeto)).build();
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        	LOG.severe("Erro não tratado");
	        	return Response.serverError().build();
	        }
	    }
		
		
		@GET
		@Path("/{idCasoDeUso}")
		public Response buscar(@PathParam("idCasoDeUso") final Integer idCasoDeUso) {
			try {
				return Response.ok(bc.find(idCasoDeUso)).build();
			}
			catch(Exception e) {
				e.printStackTrace();
				LOG.severe("Erro não tratado");
				return Response.serverError().build();
			}
		}
		
		@PUT
		@Path("/{idProjeto}")
		@Transactional
		public Response alterar(CasoDeUso c, @PathParam("idCasoDeUso") final Integer idCasoDeUso) {
			try {
				bc.mergeHalf(idCasoDeUso, c);
				return Response.ok().build();
			}
			catch(Exception e) {
				e.printStackTrace();
				LOG.severe("Erro não tratado");
				return Response.serverError().build();
			}
		}
		
		@DELETE
		@Path("/{idCasoDeUso}")
		@Transactional
		public Response excluir(@PathParam("idCasoDeUso") final Integer idCasoDeUso) {
			try {
				bc.remove(idCasoDeUso);
				return Response.ok().build();
			}
			catch(Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}

}
