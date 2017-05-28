package ar.com.tacs.grupo5.frba.utn.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.dao.impl.FavMoviesDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.impl.MovieDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMoviesRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.MovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoTestConfiguration.class})
@PropertySource("classpath:application.properties")
public class MovieDaoTest extends AbstractDaoTest{
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;
	
	private MovieDaoImpl movieDao;
	
	private FavMoviesDaoImpl favMoviesDao;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private FavMoviesRepository favMoviesRepository;
	
	@Before
	public void beforeEach()
	{
		MockitoAnnotations.initMocks(getClass());
		insertInitialData();
		favMoviesDao = new FavMoviesDaoImpl(favMoviesRepository);
		movieDao = new MovieDaoImpl(movieRepository,favMoviesDao);
	}
	
	@After
	public void afterEach()
	{
		deleteAllData();
	}

	@Test
	public void testAddMovieToFavMovies() {
		MovieEntity addedMovie = new MovieEntity();
		
		addedMovie.setFavMovie(favMoviesDao.getFavMovie("2"));
		addedMovie.setIdMovie("123");
		
		movieDao.saveMovie(addedMovie);

		Assert.assertEquals(movieDao.getMovie("2", "123"), addedMovie);
	}

	@Test
	public void testRemoveMovieFromFavMovies() {
		Movie deletedMovie = new Movie();
		
		deletedMovie.setFavMovieId("bac25bb4c3dd");
		deletedMovie.setMovieId("2");
		deletedMovie.setId("100");
		
		movieDao.deleteMovie(deletedMovie);
		
		FavMoviesEntity favMovies = favMoviesDao.getFavMovie("bac25bb4c3dd");

	}
}
