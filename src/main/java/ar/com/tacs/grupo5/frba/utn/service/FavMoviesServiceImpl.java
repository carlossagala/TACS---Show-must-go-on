package ar.com.tacs.grupo5.frba.utn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;


@Component
public class FavMoviesServiceImpl implements FavMoviesService {
	@Autowired
	private FavMoviesDao favMoviesDao;
	
	@Override
	public FavMovie updateFavMovie(String newTitle, String idFavMovie) throws ResourceNotFound {
		FavMovie modifiedFavMovie = favMoviesDao.getFavMovie(idFavMovie);
		
		if (modifiedFavMovie == null)
			throw new ResourceNotFound();
		
		modifiedFavMovie.setName(newTitle);

		favMoviesDao.saveFavMovie(modifiedFavMovie);
		
		return modifiedFavMovie;
	}

	@Override
	public boolean deleteFavMovie(String idFavMovie) throws ResourceNotFound {
		FavMovie deletedFavMovie = favMoviesDao.getFavMovie(idFavMovie);
		
		if (deletedFavMovie == null)
			throw new ResourceNotFound();
		
		return favMoviesDao.deleteFavMovie(deletedFavMovie);
	};
}
