package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;
import java.util.Set;

import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.User;

public interface FavMoviesService {
	
	FavMovies createNewFavMovieList(String title, User user) ;

	FavMovies updateFavMovie(String newTitle, String idFavMovie) throws ResourceNotFound;

	void deleteFavMovie(String idFavMovie) throws ResourceNotFound;

	FavMovies getFavMovieDetail(String idFavMovie) throws ResourceNotFound;
	
	List<Movie> getListIntersection(String id1, String id2) throws ResourceNotFound;

	Long countByUser(User user);

	User getUserById(String id1);
	
	void getFavMoviesByUser(String userId, int page, PagedResponse resp);

}
