package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.ArrayList;
import java.util.List;

import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

public class MovieDaoImpl implements MovieDao {

	@Override
	public List<Movie> mapMovies(List<MovieEntity> movieList) {
		List<Movie> returnList = new ArrayList<Movie>();
		
		for(MovieEntity movEnt:movieList){
			Movie mov = new Movie();
			
			mov.setId(movEnt.getId());
			mov.setTitle(movEnt.getTitle());
			
			returnList.add(mov);
		}
		
		return returnList;
	}

}
