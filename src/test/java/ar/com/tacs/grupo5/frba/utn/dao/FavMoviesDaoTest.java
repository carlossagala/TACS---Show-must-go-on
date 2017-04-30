package ar.com.tacs.grupo5.frba.utn.dao;
import org.hsqldb.util.DatabaseManagerSwing;
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
//		DatabaseManagerSwing.main(
//					new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
		
		user = this.userDao.getUserById("2");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveFavMovies() {
		FavMoviesEntity savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMoviesEntity("Mi Lista Test", user));
		
		Assert.assertNotNull(savedFavMovie);
		Assert.assertTrue(savedFavMovie.getName().equals("Mi Lista Test"));
		Assert.assertEquals(5,favMoviesDao.getFavMoviesByUser(user).size());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetFavMovies() {
		Assert.assertEquals(4,favMoviesDao.getFavMoviesByUser(user).size());
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
//
//	@Test
//	public void testDeleteFavMovies() {
//		User savedUser = this.userDao.saveUser(new User("userrrr4", "userrrr4", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie);
//		savedUser = this.userDao.saveUser(savedUser);
//
//		try {
//			favMoviesService.deleteFavMovie(savedFavMovie.getId());
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//
//		Assert.assertNull(this.favMoviesDao.getFavMovie(savedFavMovie.getId()));
//	}
//
//	@Test
//	public void testDeleteFavMoviesNotFound() {
//		User savedUser = this.userDao.saveUser(new User("userrrr5", "userrrr5", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie);
//		savedUser = this.userDao.saveUser(savedUser);
//
//		try {
//			favMoviesService.deleteFavMovie(savedFavMovie.getId());
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//
//		// Intento borrar la entidad una segunda vez, en caso de que haya
//		// funcionado el primer delete,
//		// debería tirar la excepción
//		try {
//			favMoviesService.deleteFavMovie(savedFavMovie.getId());
//			fail();
//		} catch (ResourceNotFound e) {
//		}
//	}
//
//	@Test
//	public void testAddMovieToFavMovies() {
//		User savedUser = this.userDao.saveUser(new User("userAddMovie", "userAddMovie", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao
//				.saveFavMovie(new FavMovie("Una lista para dias de lluvia", savedUser.getId()));
//		String favMovieId = savedFavMovie.getId();
//		Movie addedMovie = null;
//		try {
//			addedMovie = movieService.addMovie(favMovieId, "123");
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//		Assert.assertNotNull(this.movieDao.getMovie(favMovieId,"123"));
//	}
//
//	@Test
//	public void testRemoveMovieFromFavMovies() {
//		User savedUser = this.userDao.saveUser(new User("userRemoveMovie", "userRemoveMovie", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao
//				.saveFavMovie(new FavMovie("Una lista para dias de lluvia", savedUser.getId()));
//		String favMovieId = savedFavMovie.getId();
//		Movie addedMovie = null;
//		try {
//			addedMovie = movieService.addMovie(favMovieId, "123");
//			Assert.assertNotNull(this.movieDao.getMovie(favMovieId,"123"));
//			this.movieService.removeMovie(favMovieId, "123");
//			Assert.assertNull(this.movieDao.getMovie(favMovieId,"123"));
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//
//	}
//
//	@Test
//	public void testGetFavMoviesDetail() {
//		FavMovie getFavMovie = new FavMovie();
//
//		User savedUser = this.userDao.saveUser(new User("usrGetFavDetail", "usrGetFavDetail", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie);
//		savedUser = this.userDao.saveUser(savedUser);
//
//		try {
//			getFavMovie = favMoviesService.getFavMovieDetail(savedFavMovie.getId());
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//
//		Assert.assertTrue(getFavMovie.getName() == "Mi Primera Lista");
//		Assert.assertTrue(getFavMovie.getUserId() == savedUser.getId());
//		Assert.assertTrue(getFavMovie.getId() == savedFavMovie.getId());
//	}
}
