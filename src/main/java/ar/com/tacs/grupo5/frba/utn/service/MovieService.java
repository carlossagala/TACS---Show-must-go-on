package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.Actor;
import ar.com.tacs.grupo5.frba.utn.models.Images;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.Reviews;

public interface MovieService {

	public Reviews getReviews(String id);

	public Images getImages(String id);

	public Movie getMovieDetail(String id);

	public List<Actor> getMovieActors(String id);

}
