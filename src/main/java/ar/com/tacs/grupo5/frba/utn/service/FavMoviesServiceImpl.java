package ar.com.tacs.grupo5.frba.utn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.MovieDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMoviesMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;


@Component
public class FavMoviesServiceImpl implements FavMoviesService {
	@Autowired
	private FavMoviesDao favMoviesDao;
	
	@Autowired
	private FavMoviesMapper favMoviesMapper;
	
	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public FavMovies updateFavMovie(String newTitle, String idFavMovie) throws ResourceNotFound {
		FavMovies modifiedFavMovie = favMoviesMapper.entityToDto(favMoviesDao.getFavMovie(idFavMovie));
		
		if (modifiedFavMovie == null)
			throw new ResourceNotFound();
		
		modifiedFavMovie.setName(newTitle);

		favMoviesDao.saveFavMovie(favMoviesMapper.dtoToEntity(modifiedFavMovie));
		
		return modifiedFavMovie;
	}

	@Override
	public boolean deleteFavMovie(String idFavMovie) throws ResourceNotFound {
		return false;
//		FavMovie deletedFavMovie = favMoviesDao.getFavMovie(idFavMovie);
//		
//		if (deletedFavMovie == null)
//			throw new ResourceNotFound();
//		
//		return userDao.deleteFavMovies(deletedFavMovie.getUserId(), deletedFavMovie.getId());
	}

	@Override
	public FavMovies getFavMovieDetail(String idFavMovie) throws ResourceNotFound {
		FavMovies returnFavMovie = favMoviesMapper.entityToDto(favMoviesDao.getFavMovie(idFavMovie));
		
		if (returnFavMovie == null)
			throw new ResourceNotFound();
		
		return returnFavMovie;
	}

//	@Override
//	public Movie addMovie(String idFavMovie, String movieId) throws ResourceNotFound {
//		FavMovie favMovie = favMoviesDao.getFavMovie(idFavMovie);
//		if(favMovie == null)
//		{
//			throw new ResourceNotFound();
//		}
//		Movie movie = new Movie();
//		movie.setFavMovieId(idFavMovie);
//		movie.setMovieId(movieId);
//		return movieDao.saveMovie(movie);
//
//	}
//
//	@Override
//	public void removeMovie(String idFavMovie, String movieId) throws ResourceNotFound {
//		Movie movie = new Movie();
//		movie.setFavMovieId(idFavMovie);
//		movie.setMovieId(movieId);
//		movieDao.deleteMovie(movie);
//	};
}
