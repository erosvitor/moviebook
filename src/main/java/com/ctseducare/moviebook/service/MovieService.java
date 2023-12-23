package com.ctseducare.moviebook.service;

import java.util.List;

import org.jboss.logging.Logger;

import com.ctseducare.moviebook.exception.ResourceNotFoundException;
import com.ctseducare.moviebook.model.Movie;
import com.ctseducare.moviebook.repository.MovieRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovieService {

	private Logger logger = Logger.getLogger(MovieService.class);
	
    @Inject
    MovieRepository repository;
       
    @Transactional
    public Movie create(Movie movie) {
        repository.persist(movie);
        return movie;
    }
    
    public List<Movie> readAll() {
        return repository.findAll().list();
    }
    
    @Transactional
    public Movie update(Movie movie) throws ResourceNotFoundException {
    	Movie movieFound = repository.findById(movie.getId().longValue());
    	if (movieFound == null) {
    		logger.error("Movie does not exist!");
    		throw new ResourceNotFoundException("Movie does not exist!"); 
    	}
    	
        movieFound.setTitle(movie.getTitle());
        movieFound.setGenre(movie.getGenre());
        movieFound.setYear(movie.getYear());
        movieFound.setMainActor(movie.getMainActor());
        movieFound.setWatchedWhen(movie.getWatchedWhen());
        movieFound.setWatchedWhere(movie.getWatchedWhere());
        movieFound.setSynopse(movie.getSynopse());
        
        return movieFound;
    }
    
    @Transactional
    public Movie delete(Integer id) throws ResourceNotFoundException {
    	Movie movieFound = repository.findById(id.longValue());
    	if (movieFound == null) {
    		logger.error("Movie does not exist!");
    		throw new ResourceNotFoundException("Movie does not exist!"); 
    	}
        repository.deleteById(id.longValue());
        return movieFound;
    }
    
}