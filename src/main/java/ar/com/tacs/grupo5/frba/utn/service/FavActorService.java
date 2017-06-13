package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.User;

public interface FavActorService {
	
	void addFavActor(User user, String idActor);
	
	void deleteFavActor(User user, String idActor);
	
	void getFavActors(String id, int page, PagedResponse resp);

	List<String> getFavActorsId(String userId, int page);

	Long countByUser(User user);
	void getAllFavActors(String id, PagedResponse resp);
	
	List<String> getFavActorsIds(String userId);
	
}
