package ar.com.tacs.grupo5.frba.utn.service;

import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

public interface FavMoviesService {

	FavMovie updateFavMovie(String newTitle, String idFavMovie) throws ResourceNotFound;

	boolean deleteFavMovie(String idFavMovie) throws ResourceNotFound;
}
