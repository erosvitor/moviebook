package com.ctseducare.moviebook.unittest.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ctseducare.moviebook.exception.ResourceNotFoundException;
import com.ctseducare.moviebook.exception.response.ExceptionResponse;
import com.ctseducare.moviebook.model.Movie;
import com.ctseducare.moviebook.resource.MovieResource;
import com.ctseducare.moviebook.service.MovieService;
import com.ctseducare.moviebook.validation.ValidationErrorResponse;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class MovieResourceTest {
	
	@InjectMock
	MovieService service;
	
	@Inject
	MovieResource resource;
	
	@Test
	void testPost() throws Exception {
		Movie movieToInsert = new Movie();
		movieToInsert.setTitle("Momentum");
		movieToInsert.setGenre("Ação");
		movieToInsert.setYear(2015);
		movieToInsert.setMainActor("Olga Kurylenko");
		movieToInsert.setWatchedWhen(LocalDate.of(2023, 1, 5));
		movieToInsert.setWatchedWhere("YouTube");
		movieToInsert.setSynopse(null);

		Movie movieInserted = new Movie();
		movieInserted.setId(1);
		movieInserted.setTitle("Momentum");
		movieInserted.setGenre("Ação");
		movieInserted.setYear(2015);
		movieInserted.setMainActor("Olga Kurylenko");
		movieInserted.setWatchedWhen(LocalDate.of(2023, 1, 5));
		movieInserted.setWatchedWhere("YouTube");
		movieInserted.setSynopse(null);
		
		Mockito.when(service.create(movieToInsert)).thenReturn(movieInserted);

		Response response = resource.create(movieToInsert);
	    Movie m = (Movie)response.getEntity();

		assertNotNull(response);
	    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	    assertEquals(1, m.getId());
	}
	
	@Test
	void testPostWithDataValidationFailure() throws Exception {
		Movie movieToInsert = new Movie();

		Response response = resource.create(movieToInsert);
		ValidationErrorResponse ver = (ValidationErrorResponse)response.getEntity();

		assertNotNull(response);
	    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	    assertEquals("Validation Error", ver.getMessage());
	    assertNotNull(ver.getErrors());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void testGet() throws Exception {
		Movie movie = new Movie();
		movie.setId(1);
		movie.setTitle("Momentum");
		movie.setGenre("Ação");
		movie.setYear(2015);
		movie.setMainActor("Olga Kurylenko");
		movie.setWatchedWhen(LocalDate.of(2023, 1, 5));
		movie.setWatchedWhere("YouTube");
		movie.setSynopse(null);
		
		List<Movie> movies = Arrays.asList(movie);
		
		Mockito.when(service.readAll()).thenReturn(movies);
		
		Response response = resource.readAll();
		
		assertNotNull(response);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(1, ((List<Movie>)response.getEntity()).size());
	}
	
	@Test
	void testPut() throws Exception {
		Movie movieToUpdate = new Movie();
		movieToUpdate.setId(1);
		movieToUpdate.setTitle("Momentum");
		movieToUpdate.setGenre("Ação");
		movieToUpdate.setYear(2015);
		movieToUpdate.setMainActor("Olga Kurylenko");
		movieToUpdate.setWatchedWhen(LocalDate.of(2023, 1, 5));
		movieToUpdate.setWatchedWhere("YouTube");
		movieToUpdate.setSynopse("Excelente filme");

		Response response = resource.update(movieToUpdate);
		
		assertNotNull(response);
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}
	
	@Test
	void testPutWithMovieDoesNotExist() throws Exception {
		Movie movieToUpdate = new Movie();
		movieToUpdate.setId(2);
		movieToUpdate.setTitle("Momentum");
		movieToUpdate.setGenre("Ação");
		movieToUpdate.setYear(2015);
		movieToUpdate.setMainActor("Olga Kurylenko");
		movieToUpdate.setWatchedWhen(LocalDate.of(2023, 1, 5));
		movieToUpdate.setWatchedWhere("YouTube");
		movieToUpdate.setSynopse(null);
		
		Mockito.when(service.update(movieToUpdate)).thenThrow(new ResourceNotFoundException("Movie does not exist!"));
		
		Response response = resource.update(movieToUpdate);
		ExceptionResponse er = (ExceptionResponse)response.getEntity();
		
		assertNotNull(response);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("Movie does not exist!", er.getReason());
	}
	
	@Test
	void testDelete() throws Exception {
		Response response = resource.delete(1);
		
		assertNotNull(response);
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}
	
	@Test
	void testDeleteWithTaskDoesNotExist() throws Exception {
		int id = 2;
		
		Mockito.when(service.delete(id)).thenThrow(new ResourceNotFoundException("Movie does not exist!"));
		
		Response response = resource.delete(id);
		
		assertNotNull(response);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

}
