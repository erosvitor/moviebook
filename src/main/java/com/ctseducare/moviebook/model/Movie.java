package com.ctseducare.moviebook.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = -5488965245548574736L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 80, nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    @Column(name = "genre", length = 60, nullable = false)
    @NotBlank(message = "Genre is required")
    private String genre;

    @Column(name = "year", nullable = false)
    @NotNull(message = "Year is required")
    private Integer year;

    @Column(name = "main_actor", length = 80, nullable = false)
    @NotBlank(message = "Main Actor is required")
    private String mainActor;
    
    @Column(name = "watched_when", nullable = false)
    @NotNull(message = "Watched When is required")
    private LocalDate watchedWhen;

    @Column(name = "watched_where", length = 20, nullable = false)
    @NotNull(message = "Watched Where is required")
    private String watchedWhere;

    @Column(name = "synopse", nullable = true)
    private String synopse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMainActor() {
		return mainActor;
	}

	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

	public LocalDate getWatchedWhen() {
		return watchedWhen;
	}

	public void setWatchedWhen(LocalDate watchedWhen) {
		this.watchedWhen = watchedWhen;
	}

	public String getWatchedWhere() {
		return watchedWhere;
	}

	public void setWatchedWhere(String watchedWhere) {
		this.watchedWhere = watchedWhere;
	}

	public String getSynopse() {
		return synopse;
	}

	public void setSynopse(String synopse) {
		this.synopse = synopse;
	}
    
}
