package ar.com.tacs.grupo5.frba.utn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMoviesMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.User;


@Component
@Transactional
public class FavMoviesServiceImpl implements FavMoviesService {
	
	private FavMoviesDao favMoviesDao;
	private FavMoviesMapper favMoviesMapper;
	private MovieMapper movieMapper;
	private UserMapper userMapper;
	private UserDao userDao;
	
	@Autowired
	public FavMoviesServiceImpl(FavMoviesDao favMoviesDao, FavMoviesMapper favMoviesMapper, MovieMapper movieMapper,UserMapper userMapper, UserDao userDao) {
		this.favMoviesDao = favMoviesDao;
		this.favMoviesMapper = favMoviesMapper;
		this.movieMapper = movieMapper;
		this.userMapper = userMapper;
		this.userDao = userDao;
	}
	
	@Override
	public FavMovies createNewFavMovieList(String title, User user) {
		FavMovies favMovies = new FavMovies();
		favMovies.setName(title);
		favMovies.setUserId(user.getId());
		FavMoviesEntity favMoviesEntity = favMoviesMapper.dtoToEntity(favMovies);
		FavMoviesEntity createdFavMovie = favMoviesDao.saveFavMovie(favMoviesEntity);
		favMovies = favMoviesMapper.entityToDto(createdFavMovie);
		return favMovies;
	}
	
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
	public void deleteFavMovie(String idFavMovie) throws ResourceNotFound {
		FavMoviesEntity favMovieToDelete = favMoviesDao.getFavMovie(idFavMovie);
		
		if (favMovieToDelete == null)
			throw new ResourceNotFound();
		
		favMoviesDao.deleteFavMovies(favMovieToDelete.getId());
	}

	@Override
	@Transactional
	public FavMovies getFavMovieDetail(String idFavMovie) throws ResourceNotFound {
		FavMovies returnFavMovie = favMoviesMapper.entityToDto(favMoviesDao.getFavMovie(idFavMovie));
		
		if (returnFavMovie == null)
			throw new ResourceNotFound();
		
		return returnFavMovie;
	}
	
	public List<Movie> getListIntersection(String id1, String id2) throws ResourceNotFound {
		FavMoviesEntity favMovieOne = favMoviesDao.getFavMovie(id1);
		if(favMovieOne == null)
			throw new ResourceNotFound("FavMovieEntity with id "+id1+" not found");
		FavMoviesEntity favMovieTwo = favMoviesDao.getFavMovie(id2);
		if(favMovieTwo == null)
			throw new ResourceNotFound("FavMovieEntity with id "+id2+" not found");
//		List<MovieEntity> intersectionList = favMovieOne.getMovies().stream().filter(favMovieTwo.getMovies()::contains).collect(Collectors.toList());
		List<Movie> movies = new ArrayList<>();
//		for (MovieEntity movieEntity : intersectionList) {
//			movies.add(movieMapper.entityToDto(movieEntity));
//		}
		

		
		
		return movies;
	}

	@Override
	public Long countByUser(User user) {
		return favMoviesDao.countByUser(userMapper.dtoToEntity(user));
	}

	@Override
	public User getUserById(String id) {
		FavMoviesEntity favMovie = favMoviesDao.getFavMovie(id);
		if(favMovie==null){
			return null;
		}
		return userMapper.entityToDto(favMovie.getUser());
	}

	@Override
	public void getFavMoviesByUser(String userId, int page, PagedResponse resp) {
		UserEntity userEntity = userDao.getUserById(userId);
		
		Page<FavMoviesEntity> favMovies = favMoviesDao.getFavMoviesByUser(userEntity, page-1);
		if(favMovies==null){
			resp.setTotalResults(0L);
			return;
		}
		resp.setData(favMovies.getContent().stream().map(FavMoviesEntity::getId).collect(Collectors.toList()));
		resp.setPage(page);
		resp.setTotalPages(favMovies.getTotalPages());
		resp.setTotalResults(favMovies.getTotalElements());
		return ;
	}

}
