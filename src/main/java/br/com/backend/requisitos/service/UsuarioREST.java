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

import br.com.backend.requisitos.bc.UsuarioBC;
import br.com.backend.requisitos.entity.Usuario;
import io.swagger.util.Json;

@Path("usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioREST extends AbstractREST<Usuario, Integer> {
	private static final Logger LOG = getLogger(UsuarioREST.class.getName());
		
	@POST
    public Response create(Usuario u) {
        try {
        	((UsuarioBC)bc).create(u);
        	return Response.ok().entity(u).build();
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
        	return Response.ok(((UsuarioBC) bc).list()).build();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	LOG.severe("Erro não tratado");
        	return Response.serverError().build();
        }
    }
	
	@GET
	@Path("/{idUsuario}")
	public Response buscar(@PathParam("idUsuario") final Integer idUsuario) {
		try {
			return Response.ok(bc.find(idUsuario)).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/{idUsuario}")
	@Transactional
	public Response alterar(Usuario u, @PathParam("idUsuario") final Integer idUsuario) {
		try {
			bc.mergeHalf(idUsuario, u);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			LOG.severe("Erro não tratado");
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/{idUsuario}")
	@Transactional
	public Response excluir(@PathParam("idUsuario") final Integer idUsuario) {
		try {
			bc.remove(idUsuario);
			return Response.ok().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	@GET
	@Path("/{idUsuario}/projetos")
    public Response listarProjetos(@PathParam("idUsuario") final Integer idUsuario) {
        try { 
        	return Response.ok(((UsuarioBC)bc).findByProjectsForUser(idUsuario)).build();
        }
        catch(Exception error) {
        	error.printStackTrace();
        	LOG.severe(error.getMessage());
        	return Response.serverError().entity(Json.pretty(error.getMessage())).build();
        }
    }
	
}
