package ar.com.tacs.grupo5.frba.utn.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.MovieDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.impl.FavMoviesDaoImpl;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMoviesMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.User;

public class FavMoviesServiceTest {
	
	
	private FavMoviesDao favMoviesDao = Mockito.mock(FavMoviesDaoImpl.class);
	private FavMoviesMapper favMoviesMapper = Mockito.mock(FavMoviesMapper.class);
	private MovieMapper movieMapper = Mockito.mock(MovieMapper.class);
	private UserMapper userMapper = Mockito.mock(UserMapper.class);
	private UserDao userDao = Mockito.mock(UserDao.class);
	private MovieDao movieDao = Mockito.mock(MovieDao.class);
	
	private FavMoviesService favMoviesService = null;
	
	private UserEntity userEnt = null;
	private User user = null;
	private UserEntity otherUserEnt = null;
	private User otherUser = null;
	private FavMoviesEntity favMoviesEnt = null;
	private FavMovies favMovies = null;
	private FavMoviesEntity otherFavMoviesEnt = null;
	private FavMovies otherFavMovies = null;
	
	private void prepareContext()
	{
		userEnt = new UserEntity("1", "admin", "admin", "admin");
		user = new User("1", "admin");
		otherUserEnt = new UserEntity("3", "user3", "user3", "user");
		otherUser = new User("3","user3");
		
		favMoviesEnt = new FavMoviesEntity("Lista de pruebas", userEnt);
		favMovies = new FavMovies("Lista de pruebas", "1");
		Set<Movie> moviesDeLaFavMovie1 = new HashSet<>();
		Movie movie1Lista1 = new Movie("1", "1");
		moviesDeLaFavMovie1.add(movie1Lista1);
		Movie movie2Lista1 = new Movie("2", "1");
		moviesDeLaFavMovie1.add(movie2Lista1);
		Movie movie3Lista1 = new Movie("3", "1");
		moviesDeLaFavMovie1.add(movie3Lista1);
		favMovies.setMovies(moviesDeLaFavMovie1);
		Set<MovieEntity> moviesDeFavMovie1 = new HashSet<>();
		MovieEntity movie1favMovie1 = crearMovie(favMoviesEnt,"1","1");
		MovieEntity movie2favMovie1 = crearMovie(favMoviesEnt,"2","2");
		MovieEntity movie3favMovie1 = crearMovie(favMoviesEnt,"3","3");
		moviesDeFavMovie1.add(movie1favMovie1);
		moviesDeFavMovie1.add(movie2favMovie1);
		moviesDeFavMovie1.add(movie3favMovie1);
		
		otherFavMoviesEnt = new FavMoviesEntity("Otra lista", otherUserEnt);
		otherFavMovies = new FavMovies("Otra lista", "3");
		Set<Movie> moviesDeLaFavMovie3 = new HashSet<>();
		Movie movie1Lista3 = new Movie("1", "1");
		Movie movie2Lista3 = new Movie("3", "1");
		Movie movie3Lista3 = new Movie("4", "1");
		moviesDeLaFavMovie3.add(movie1Lista3);
		moviesDeLaFavMovie3.add(movie2Lista3);
		moviesDeLaFavMovie3.add(movie3Lista3);
		otherFavMovies.setMovies(moviesDeLaFavMovie1);
		Set<MovieEntity> moviesEnt3 = new HashSet<>();
		MovieEntity movieEnt1favMovie3 = crearMovie(otherFavMoviesEnt,"1","1");
		MovieEntity movieEnt3favMovie3 = crearMovie(otherFavMoviesEnt,"3","3");
		MovieEntity movieEnt4favMovie3 = crearMovie(otherFavMoviesEnt,"4","4");
		moviesEnt3.add(movieEnt1favMovie3);
		moviesEnt3.add(movieEnt3favMovie3);
		moviesEnt3.add(movieEnt4favMovie3);
		
		favMoviesService = new FavMoviesServiceImpl(favMoviesDao, favMoviesMapper, movieMapper, userMapper, userDao,movieDao);
		
		
		Mockito.when(favMoviesDao.saveFavMovie(Mockito.any())).thenReturn(favMoviesEnt);
		Mockito.when(favMoviesMapper.dtoToEntity(Mockito.any())).thenReturn(favMoviesEnt);
		Mockito.when(favMoviesMapper.entityToDto(favMoviesEnt)).thenReturn(favMovies);
		Mockito.when(movieMapper.entityToDto(movie1favMovie1)).thenReturn(movie1Lista1);
		Mockito.when(movieMapper.entityToDto(movie2favMovie1)).thenReturn(movie2Lista1);
		Mockito.when(movieMapper.entityToDto(movie3favMovie1)).thenReturn(movie3Lista1);
		Mockito.when(movieMapper.entityToDto(movieEnt1favMovie3)).thenReturn(movie1Lista3);
		Mockito.when(movieMapper.entityToDto(movieEnt3favMovie3)).thenReturn(movie2Lista3);
		Mockito.when(movieMapper.entityToDto(movieEnt4favMovie3)).thenReturn(movie3Lista3);
		Mockito.when(userMapper.dtoToEntity(user)).thenReturn(userEnt);
		Mockito.when(userMapper.entityToDto(userEnt)).thenReturn(user);
		Mockito.when(favMoviesDao.getFavMovie("1")).thenReturn(favMoviesEnt);
		Mockito.when(favMoviesDao.getFavMovie("2")).thenReturn(null);
		Mockito.when(favMoviesDao.getFavMovie("3")).thenReturn(otherFavMoviesEnt);
		Mockito.when(favMoviesDao.countByUser(userEnt)).thenReturn(Long.valueOf(3));
	}

	private MovieEntity crearMovie(FavMoviesEntity favMoviesEnt, String id, String idMovie) {
		MovieEntity movie = new MovieEntity();
		movie.setFavMovie(favMoviesEnt);
		movie.setId(id);
		movie.setIdMovie(idMovie);
		return movie;
	}
	
	@Test
	public void testCreateNewFavMovieList()
	{
		prepareContext();
		FavMovies created = favMoviesService.createNewFavMovieList("Lista de pruebas", user);
		Assert.assertNotNull(created);
		Assert.assertTrue(created.getName().equalsIgnoreCase("Lista de pruebas"));
	}
	
	@Test
	public void testUpdateFavMovie() throws ResourceNotFound
	{
		prepareContext();
		FavMovies updateFavMovie = favMoviesService.updateFavMovie("Nuevo título", "1");
		Assert.assertNotNull(updateFavMovie);
		Assert.assertTrue(updateFavMovie.getName().equalsIgnoreCase("Nuevo título"));
	}
	
	@Test(expected=ResourceNotFound.class)
	public void testUpdateFavMovieThatDoesNotExist() throws ResourceNotFound
	{
		prepareContext();
		FavMovies updateFavMovie = favMoviesService.updateFavMovie("Nuevo título", "2");
		Assert.assertNull(updateFavMovie);
	}
	
	@Test
	public void testDeleteFavMovie() throws ResourceNotFound
	{
		prepareContext();
		favMoviesService.deleteFavMovie("1");
	}
	
	@Test(expected=ResourceNotFound.class)
	public void testDeleteFavMovieThatDoesNotExist() throws ResourceNotFound
	{
		prepareContext();
		favMoviesService.deleteFavMovie("2");
	}
	
	@Test
	public void testGetFavMovieDetail() throws ResourceNotFound
	{
		prepareContext();
		FavMovies favMovieDetail = favMoviesService.getFavMovieDetail("1");
		Assert.assertNotNull(favMovieDetail);
		Assert.assertTrue(favMovieDetail.getName().equalsIgnoreCase("Lista de pruebas"));
	}
	
	@Test(expected = ResourceNotFound.class)
	public void testGetFavMovieDetailThatDoesNotExist() throws ResourceNotFound
	{
		prepareContext();
		favMoviesService.getFavMovieDetail("2");
	}
	

	@Test
	public void testGetListIntersection() throws ResourceNotFound
	{
		prepareContext();
		List<Movie> intersection = favMoviesService.getListIntersection("1", "3");
		Assert.assertNotNull(intersection);
//		Assert.assertTrue(intersection.size() == 2);
	}
	
	@Test(expected = ResourceNotFound.class)
	public void testGetListIntersectionResourceNotFound() throws ResourceNotFound
	{
		prepareContext();
		favMoviesService.getListIntersection("100", "200");
	}
	
	
	@Test
	public void testCountByUser()
	{
		prepareContext();
		Long count = favMoviesService.countByUser(user);
		Assert.assertNotNull(count);
		Assert.assertTrue(count == 3);
	}
	
	@Test
	public void testGetUserById()
	{
		prepareContext();
		User userById = favMoviesService.getUserById("1");
		Assert.assertNotNull(userById);
		Assert.assertTrue(userById.getUserName().equals("admin"));
	}


}
