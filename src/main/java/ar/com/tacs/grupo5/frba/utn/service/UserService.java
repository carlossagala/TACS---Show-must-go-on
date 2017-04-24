package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.User;

public interface UserService {

	List<User> getAllUsers();

	User getUserById(String id);

	User saveUser(User user);

	User findByUserNameAndPass(String userName, String pass);

	User findByUserName(String userName);

//	Set<FavMovie> getUserFavMovies(String id);
//	List<Movie> getListIntersection(String id1, String id2);
//
//	void getFavActors(String userId,int page,PagedResponse resp);
//	void addFavActor(String idUser,String idActor);
//	void deleteFavActor(String idUser,String idActor);
//	
//	FavMovie createNewFavMovieList(String title, User user) ;
//	List<String> getFavActorsId(String userId,int page);
}
