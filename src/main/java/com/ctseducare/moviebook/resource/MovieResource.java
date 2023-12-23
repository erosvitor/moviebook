package com.ctseducare.moviebook.resource;

import java.util.Set;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ctseducare.moviebook.exception.ResourceNotFoundException;
import com.ctseducare.moviebook.exception.response.ExceptionResponse;
import com.ctseducare.moviebook.model.Movie;
import com.ctseducare.moviebook.service.MovieService;
import com.ctseducare.moviebook.validation.ValidationErrorHelper;
import com.ctseducare.moviebook.validation.ValidationErrorResponse;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/movies")
@Tag(name = "Movies", description = "Registro, Listagem, Alteração e Remoção de filmes assistidos")
public class MovieResource {
	
	@Inject
	MovieService service;
	
	@Inject
	Validator validator;
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Registra um filme assistido", description = "Após o filme ser registrado é retornado os dados do filme com o identificador preenchido.")
    @APIResponses(
      value = {
        @APIResponse(responseCode = "201", description = "Dados do filme com o identificador preenchido", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Movie.class))),
        @APIResponse(responseCode = "400", description = "Erro informando problemas com os dados da requisição", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ValidationErrorResponse.class)))
    })
    public Response create(Movie movie) {
    	Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
    	if (violations.isEmpty()) {
        	Movie result = service.create(movie);
            return Response.status(Status.CREATED).entity(result).build();
    	}
        ValidationErrorResponse ver = ValidationErrorHelper.createFrom(violations);
        return Response.status(Status.BAD_REQUEST).entity(ver).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista filmes registrados.", description = "Listagem dos filmes previamente registrados.")
    @APIResponse(responseCode = "200", description = "Lista contendo os filmes", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Movie.class, type = SchemaType.ARRAY)))
    public Response readAll() {
        return Response.ok(service.readAll()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Altera dados de um filme", description = "Após os dados serem alterados é retornado o status 200 sem conteúdo.")
    @APIResponses(
      value = {
        @APIResponse(responseCode = "200"),
        @APIResponse(responseCode = "400", description = "Erro informando problemas com os dados da requisição", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ValidationErrorResponse.class))),
        @APIResponse(responseCode = "404", description = "Erro informando que o filme não existe", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public Response update(Movie movie) {
    	Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
    	if (violations.isEmpty()) {
	    	try {
	    		service.update(movie);
	    		return Response.noContent().build();
	    	} catch (ResourceNotFoundException e) {
	    		ExceptionResponse er = new ExceptionResponse(Status.NOT_FOUND.getStatusCode(), e.getMessage());
	    		return Response.status(er.getError()).entity(er).build();
	    	}
    	}
        ValidationErrorResponse ver = ValidationErrorHelper.createFrom(violations);
        return Response.status(Status.BAD_REQUEST).entity(ver).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remove um filme", description="Após o filme ser removido é retornado o status 200 sem conteúdo.")
    @APIResponses(
      value = {
        @APIResponse(responseCode = "200"),
        @APIResponse(responseCode = "404", description = "Erro informando que o filme não existe", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public Response delete(@PathParam("id") Integer id) {
    	try {
        	service.delete(id);
            return Response.noContent().build();
    	} catch (ResourceNotFoundException e) {
    		ExceptionResponse er = new ExceptionResponse(Status.NOT_FOUND.getStatusCode(), e.getMessage());
    		return Response.status(er.getError()).entity(er).build();
    	}
    }

}
