package ar.com.tacs.grupo5.frba.utn.dao;
import org.junit.Assert;
import org.junit.Before;
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
import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.MovieDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.service.FavMoviesService;
import ar.com.tacs.grupo5.frba.utn.service.MovieService;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="ar.com.tacs.grupo5.frba.utn")
public class FavMoviesDaoTest {
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;
	@SpyBean
	private FavMoviesDao favMoviesDao;
	@SpyBean
	private UserDao userDao;
	@SpyBean
	private MovieDao movieDao;
	@SpyBean
	private UserMapper userMapper;
	@SpyBean
	private MovieMapper movieMapper;
	@SpyBean
	private FavMoviesService favMoviesService;
	@SpyBean
	private MovieService movieService;
	
	private UserEntity user;
	
	@Before
	public void Before()
	{
		user = this.userDao.getUserById("2");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveFavMovies() {
		FavMoviesEntity savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMoviesEntity("Mi Lista Test", user));
		
		Assert.assertNotNull(savedFavMovie);
		Assert.assertTrue(savedFavMovie.getName().equals("Mi Lista Test"));
		Assert.assertEquals(5,favMoviesDao.getFavMoviesByUser(user,0).getContent().size());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetFavMovies() {
		Assert.assertEquals(4,favMoviesDao.getFavMoviesByUser(user,0).getContent().size());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateFavMovies() {
		FavMoviesEntity fav = favMoviesDao.getFavMovie("2");

		fav.setName("Nombre cambiado");
		
		fav = favMoviesDao.saveFavMovie(fav);

		FavMoviesEntity fav2 = favMoviesDao.getFavMovie("2");
		
		Assert.assertTrue(fav2.getName() == "Nombre cambiado");
	}
}
