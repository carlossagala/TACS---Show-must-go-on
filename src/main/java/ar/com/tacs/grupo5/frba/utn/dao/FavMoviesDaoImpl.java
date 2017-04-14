package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.ArrayList;
import java.util.List;

import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

public class FavMoviesDaoImpl implements FavMoviesDao {

	@Override
	public List<FavMovie> mapFavMovies(List<FavMovieEntity> favMoviesEntity) {
        MovieDao movieDao = new MovieDaoImpl();
		
		List<FavMovie> returnList = new ArrayList<FavMovie>();
        
		
		for(FavMovieEntity favMovieEnt: favMoviesEntity)
        {
			FavMovie favMovie = new FavMovie();
			favMovie.setName(favMovieEnt.getName());
			favMovie.setId(favMovieEnt.getId());
			favMovie.setMovies(movieDao.mapMovies(favMovieEnt.getMovies()));
			
        	returnList.add(favMovie);
        }

		return returnList;
	}

}
