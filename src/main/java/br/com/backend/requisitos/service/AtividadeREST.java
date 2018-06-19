package br.com.backend.requisitos.service;

import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demoiselle.jee.crud.AbstractREST;

import br.com.backend.requisitos.bc.AtividadeBC;
import br.com.backend.requisitos.dto.AtividadeDTO;
import br.com.backend.requisitos.entity.Atividade;
import io.swagger.util.Json;

@Path("projeto/{idProjeto}/requisito/{idRequisito}/atividade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtividadeREST extends AbstractREST<Atividade, Integer> {
	private static final Logger LOG = getLogger(AtividadeREST.class.getName());
		
	@POST
    public Response create(
		@PathParam("idRequisito") final Integer idRequisito,
		Atividade a
    ) {
        try {
        	((AtividadeBC)bc).create(a, idRequisito);
        	return Response.ok().entity(new AtividadeDTO(a)).build();
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
        	return Response.ok(((AtividadeBC)bc).list()).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	LOG.severe("Erro não tratado");
        	return Response.serverError().build();
        }
    }
	
	@GET
	@Path("/{idAtividade}")
	public Response buscar(
		@PathParam("idRequisito") final Integer idRequisito,
		@PathParam("idRequisito") final Integer idAtividade
	) {
		try {
			return Response.ok(((AtividadeBC)bc).find(idRequisito, idAtividade)).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
//	@PUT
//	@Path("/{idRequisito}")
//	@Transactional
//	public Response alterar(Requisito r, @PathParam("idRequisito") final Integer idRequisito) {
//		try {
//			bc.mergeHalf(idRequisito, r);
//			return Response.ok().build();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			LOG.severe("Erro não tratado");
//			return Response.serverError().build();
//		}
//	}
//	
//	@DELETE
//	@Path("/{idRequisito}")
//	@Transactional
//	public Response excluir(@PathParam("idRequisito") final Integer idRequisito) {
//		try {
//			bc.remove(idRequisito);
//			return Response.ok().build();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return Response.serverError().build();
//		}
//	}
}
