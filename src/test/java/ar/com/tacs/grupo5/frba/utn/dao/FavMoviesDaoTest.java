package ar.com.tacs.grupo5.frba.utn.dao;
import static org.junit.Assert.*;

//import org.hsqldb.util.DatabaseManagerSwing;
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
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

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
		Assert.assertEquals((long)5,(long)favMoviesDao.countByUser(user));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetFavMovies() {
		FavMoviesEntity getFavMovie = favMoviesDao.getFavMovie("2");

		Assert.assertEquals(getFavMovie.getName(), "Mi segunda lista");
		Assert.assertEquals(getFavMovie.getUser(), user);
		Assert.assertTrue(getFavMovie.getId() == "2");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testCountFavMovies() {
		Assert.assertEquals((long)4,(long)favMoviesDao.countByUser(user));
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

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFavMovies() {
		try {
			favMoviesDao.deleteFavMovies("2");
		} catch (ResourceNotFound e) {
			fail();
		}

		Assert.assertNull(this.favMoviesDao.getFavMovie("2"));
		Assert.assertEquals((long)3,(long)favMoviesDao.countByUser(user));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFavMoviesNotFound() {
		try {
			favMoviesDao.deleteFavMovies("37");
			fail();
		} catch (ResourceNotFound e) {
		}

     	Assert.assertEquals((long)4,(long)favMoviesDao.countByUser(user));
	}
}
