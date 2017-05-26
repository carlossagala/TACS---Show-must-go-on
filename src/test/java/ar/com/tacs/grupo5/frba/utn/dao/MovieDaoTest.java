package ar.com.tacs.grupo5.frba.utn.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="ar.com.tacs.grupo5.frba.utn")
public class MovieDaoTest {
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;
	@SpyBean
	private MovieDao movieDao;
	@SpyBean
	private FavMoviesDao favMoviesDao;

	@Test
	@Transactional
	@Rollback(true)
	public void testAddMovieToFavMovies() {
		MovieEntity addedMovie = new MovieEntity();
		
		addedMovie.setFavMovie(favMoviesDao.getFavMovie("2"));
		addedMovie.setIdMovie("123");
		
		movieDao.saveMovie(addedMovie);

		Assert.assertEquals(movieDao.getMovie("2", "123"), addedMovie);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveMovieFromFavMovies() {
		Movie deletedMovie = new Movie();
		
		deletedMovie.setFavMovieId("bac25bb4c3dd");
		deletedMovie.setMovieId("2");
		deletedMovie.setId("182e1a2cbb34");
		
		movieDao.deleteMovie(deletedMovie);
		
		FavMoviesEntity favMovies = favMoviesDao.getFavMovie("bac25bb4c3dd");

	}
}
