package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;
import java.util.Set;

import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.User;

public interface UserService {

	List<User> getAllUsers();

	User getUserById(String id);

	User saveUser(User user);

	Set<FavMovie> getUserFavMovies(String id);

	User findByUserNameAndPass(String userName, String pass);

	User findByUserName(String userName);

	List<Movie> getListIntersection(String id1, String id2);

	void getFavActors(String userId,int page,PagedResponse resp);
	void addFavActor(String idUser,String idActor);
	void deleteFavActor(String idUser,String idActor);
}
